package validators;

import java.util.regex.Pattern;
import java.time.format.*;
import java.time.*;

/*
 * A class for determining if a Swedish personal number, coordination number, or organization number is considered valid or not.
 */
public class PersonalNumberValidator {

	private static DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT);;
	
	private static final int CURRENT_YEAR = 2022;
	private static final int CURRENT_DECADE = 22;
	
	private static final int SHORT_BIRTH_DATE = 6;
	private static final int LONG_BIRTH_DATE = 8;
	
	private static final int SHORT_DATE_NO_DIVIDER = 10;
	private static final int LONG_DATE_NO_DIVIDER = 12;
			
	private static final int SHORT_DATE_WITH_DIVIDER = 11;
	private static final int LONG_DATE_WITH_DIVIDER = 13;
	
	//TODO: Consider using enums instead. Separate module?
	
	
	/*
	 *  Assumes that the given personal number conforms to one of the following formats: 
	 *  YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param personalNumber  - 
	 * 
	 * @return 
	 */
	public static boolean isValidPersonalNumber(String personalNumber) {
		if(isValidFormat(personalNumber) && isValidDate(personalNumber) && fulfillsLuhnsAlgorithm(personalNumber)) {
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * 
	 * @param idNumber  - 
	 * 
	 * @return 
	 */
	public static boolean isValidFormat(String idNumber) {
		// idNumber consists only of numbers. YYMMDDXXXX or YYYYMMDDXXXX
		String shortDateNoDivider = "[0-9]{10}";
		String longDateNoDivider = "[1-9][0-9]" + shortDateNoDivider;
		
		// idNumber may have a + or - as divider between birth date and control numbers. 
		// YYMMDD-XXXX, YYYYMMDD-XXXX, YYMMDD+XXXX or YYYYMMDD+XXXX
		String shortDateWithDivider = "[0-9]{6}(-|\\+)[0-9]{4}";
		String longDateWithDivider = "[1-9][0-9]" + shortDateWithDivider;
		
		
		if(Pattern.matches(shortDateNoDivider + "|"+ longDateNoDivider, idNumber)) {
			return true;
		}
		else if(Pattern.matches(shortDateWithDivider, idNumber)) {
			return true;
		}
		else if(Pattern.matches(longDateWithDivider, idNumber)) {
			if(idNumber.contains("-") || (idNumber.contains("+") && isPlusDividerValid(idNumber))) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 
	 * @param idNumber  - 
	 * 
	 * @return 
	 */
	private static boolean isPlusDividerValid(String idNumber) {
		int year = Integer.parseInt(idNumber.substring(0,4));
		
		if(year <= CURRENT_YEAR -100) {
			return true;
		}
		
		return false;
	}
	
	
	
	/*
	 *  Assumes that the given ID number conforms to one of the following formats: 
	 *  YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param personalNumber  - 
	 * 
	 * @return 
	 */
	public static boolean isValidDate(String personalNumber) {
		if(!isValidFormat(personalNumber)) {
			return false;
		}
		
		//TODO: Make LocalDate check that date hasn't passed yet
		String birthDate = getBirthDate(personalNumber, true);
		int year = Integer.parseInt(birthDate.substring(0, 4));
		// If date parsing fails it is not a valid date
		if(year <= CURRENT_YEAR) {
			try {
				LocalDate.parse(birthDate, dateFormat);
			} 
			catch(DateTimeParseException e) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	
	/*
	 *  Assumes that the given ID number conforms to one of the following formats: 
	 *  YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param personalNumber  - 
	 * @param addCentury  - 
	 * 
	 * @return A birth date in the format YYYYMMDD
	 */
	public static String getBirthDate(String personalNumber, boolean addCentury) {
		String[] segments = separateDateAndControlNumbers(personalNumber);
		String birthDate = segments[0];
		String delimiter = segments[1];
		
		if(birthDate.length() == SHORT_BIRTH_DATE && addCentury) {
			birthDate = addCenturyToDate(birthDate, delimiter);
		}
		else if(birthDate.length() == LONG_BIRTH_DATE && !addCentury) {
			birthDate = birthDate.substring(2);
		}
		
		return birthDate;
	}
	
	/*
	 *  Assumes that the given ID number conforms to one of the following formats: 
	 *  YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param personalNumber  - 
	 * 
	 * @return A control number in the format XXXX
	 */
	public static String getControlNumbers(String personalNumber) {
		
		return separateDateAndControlNumbers(personalNumber)[2];
	}
	
	
	/*
	 *  Assumes that the given ID number conforms to one of the following formats: 
	 *  YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param personalNumber  - 
	 * 
	 * @return An array containing the birthdate with or without the century, eventual delimiter, and control number.
	 */
	private static String[] separateDateAndControlNumbers(String personalNumber) {
		int strLength = personalNumber.length();
		String birthDate = "";
		String delimiter = "";
		String controlNums = "";
		
		/* Both conditionals separate the 4 control numbers. Second one separates the delimiter too
		 * Leave 6 or 8 birth numbers respectively.
		 */
		if(strLength == LONG_DATE_NO_DIVIDER || strLength == SHORT_DATE_NO_DIVIDER) {
			birthDate = personalNumber.substring(0, strLength -4);
			controlNums = personalNumber.substring(strLength -4, strLength);
		}
		else if(strLength == LONG_DATE_WITH_DIVIDER || strLength == SHORT_DATE_WITH_DIVIDER) {
			birthDate = personalNumber.substring(0, strLength -5);
			delimiter = personalNumber.substring(strLength -5, strLength -4);
			controlNums = personalNumber.substring(strLength -4, strLength);
		}
		
		return new String[]{birthDate, delimiter, controlNums};
	}
	
	/*
	 *  . Assumes that the given ID number conforms to the following format: YYMMDD
	 * @param personalNumber  - 
	 * @param delimiter  - 
	 * 
	 * @return A birthdate in the format of YYYYMMDD
	 */
	private static String addCenturyToDate(String personalNumber, String delimiter) {
		int strLength = personalNumber.length();
		int decade = Integer.parseInt(personalNumber.substring(0, 2));
		
		if(strLength == SHORT_BIRTH_DATE +1 && delimiter.equals("+")) {
			if(decade <= CURRENT_DECADE) {
				personalNumber = "19" + personalNumber;
			}
			else {
				personalNumber = "18" + personalNumber;
			}
			//DEFAULTS TO 1922-1900 INSTEAD OF 1822-1800
		}
		else if(strLength == SHORT_BIRTH_DATE || (strLength == SHORT_BIRTH_DATE +1 && delimiter.equals("-"))) {
			if(decade > CURRENT_DECADE) {
				personalNumber = "19" + personalNumber;
			}
			else {
				personalNumber = "20" + personalNumber;
			}
		}
		
		return personalNumber;
	}
	

	
	/*
	 * Assumes that the given ID number conforms to one of the following formats: 
	 * YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param idNumber  - 
	 * 
	 * @return 
	 */
	public static boolean fulfillsLuhnsAlgorithm(String idNumber) {
		if(!isValidFormat(idNumber)) {
			return false;
		}
		
		String date = getBirthDate(idNumber, false);
		String controlNumbers = getControlNumbers(idNumber);
		
		String multipliedNumbers = date + controlNumbers;
		int multipliedSum = 0;
		
		for(int i = 0; i < multipliedNumbers.length(); ++i) {
			int value = Integer.parseInt(String.valueOf(multipliedNumbers.charAt(i)));
			int multipliedValue= 0;
			
			if(i % 2 == 0) {
				multipliedValue = value*2;				
			}
			else {
				multipliedValue = value*1;
			}
			multipliedSum += (multipliedValue/10) + (multipliedValue % 10);
		}
		
		if(multipliedSum % 10 == 0) {
			return true;
		}
		
		return false;
	}
}
