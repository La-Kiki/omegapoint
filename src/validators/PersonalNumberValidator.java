package validators;

import java.util.regex.Pattern;
import java.time.format.*;
import java.time.*;

/*
 * A class for determining if a Swedish personal number is considered valid or not.
 */
public class PersonalNumberValidator {

	private static DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("uuuuMMdd")
													.withResolverStyle(ResolverStyle.STRICT);

	private static final int CURRENT_YEAR = 2022;
	private static final int CURRENT_DECADE = 22;
	
	//Constants for the different lengths of valid personal, coordinate, and organisation numbers.
	// Birth dates without or with century: YYMMDD or YYYYMMDD
	protected static final int SHORT_BIRTH_DATE = 6;
	protected static final int LONG_BIRTH_DATE = 8;
	
	//Without delimiters + or -
	protected static final int SHORT_DATE_NO_DIVIDER = 10;
	protected static final int LONG_DATE_NO_DIVIDER = 12;
			
	//With delimiters + or -
	protected static final int SHORT_DATE_WITH_DIVIDER = 11;
	protected static final int LONG_DATE_WITH_DIVIDER = 13;
	
	
	/* Checks if a given personal number conforms to a correct format, date, and to Luhn's algorithm.
	 * Accepts personal numbers in the format (YY)?YYMMDD[-+]?XXXX.
	 * 
	 * @param personalNumber  - The personal number to be validated
	 * 
	 * @return A boolean stating whether the given personal number is valid or not
	 */
	public static boolean isValidPersonalNumber(String personalNumber) {
		if(isValidFormat(personalNumber) && isValidBirthDate(personalNumber) && fulfillsLuhnsAlgorithm(personalNumber)) {
			return true;
		}
		
		return false;
	}
	
	
	/* Checks if a given personal, coordination or organisation number conforms to the format (YY)?YYMMDD[-+]?XXXX.
	 * 
	 * @param idNumber  - The ID number to be checked
	 * 
	 * @return A boolean stating whether the given ID number follows a valid format
	 */
	public static boolean isValidFormat(String idNumber) {
		// idNumber consists only of numbers. YYMMDDXXXX or YYYYMMDDXXXX
		String shortDateNoDivider = "[0-9]{10}";
		String longDateNoDivider = "[1-2][0-9]" + shortDateNoDivider;
		
		// idNumber may have a + or - as divider between birth date and control numbers. 
		// YYMMDD[-+]XXXX or YYYYMMDD[-+]XXXX
		String shortDateWithDivider = "[0-9]{6}(-|\\+)[0-9]{4}";
		String longDateWithDivider = "[1-2][0-9]" + shortDateWithDivider;
		
		
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
	
	/* Checks whether a year in an personal or coordination number was 100 years ago to warrant a + as delimiter.
	 * Assumes the ID number is in the format YYYYMMDD[-+]XXXX.
	 *  
	 * @param idNumber  - The ID number to be checked
	 * 
	 * @return A boolean stating whether the ID number can use a plus delimiter
	 */
	private static boolean isPlusDividerValid(String idNumber) {
		int year = Integer.parseInt(idNumber.substring(0,4));
		
		if(year <= CURRENT_YEAR -100) {
			return true;
		}
		//TODO: Use LocalDate to compare current year instead of constant
		
		return false;
	}
	
	
	
	/* Checks whether a birth date in a personal number is a past or current date. Includes leap years.
	 * Must subtract 60 from the birth date of a coordinate number to be valid.
	 * Personal number must be in the format (YY)?YYMMDD[-+]?XXXX. 
	 * 
	 * Unless year is specified with YYYY, it is assumed the year implies the latest possible century. If 
	 * using a + delimiter, the year is assumed to have occurred at least a century ago.
	 * 
	 * @param personalNumber  - The personal number to be checked.
	 * 
	 * @return A boolean stating whether the birth date in the given personal number is a past or current date.
	 */
	public static boolean isValidBirthDate(String personalNumber) {
		if(!isValidFormat(personalNumber)) {
			return false;
		}
		
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
			//TODO: Make LocalDate check that date hasn't passed yet
			return true;
		}
		
		return false;
	}
	
	
	
	/* Retrieves the birth date from an ID number in either the format YYYYMMDD or YYMMDD.
	 * Assumes the ID number is in the format (YY)?YYMMDD[-+]?XXXX. 
	 * 
	 * Unless year is specified with YYYY, it is assumed the year implies the latest possible century. If 
	 * using a + delimiter, the year is assumed to have occurred at least a century ago.
	 * 
	 * @param idNumber  - The ID number to retrieve the birth date from
	 * @param addCentury  - A boolean stating whether the birth date should include the century or not
	 * 
	 * @return A birth date in the format YYYYYMMDD or YYYMMDD
	 */
	public static String getBirthDate(String idNumber, boolean addCentury) {
		String birthDate = separateDateDelimiterControl(idNumber)[0];
		String delimiter = getDelimiter(idNumber);
		
		if(birthDate.length() == SHORT_BIRTH_DATE && addCentury) {
			birthDate = addCenturyToDate(birthDate, delimiter);
		}
		else if(birthDate.length() == LONG_BIRTH_DATE && !addCentury) {
			birthDate = birthDate.substring(2);
		}
		
		return birthDate;
	}
	
	/* Retrieves the delimiter from an personal, coordination or organisation number if present. Otherwise returns an empty string.
	 * Assumes the ID number is in the format (YY)?YYMMDD[-+]?XXXX. 
	 * 
	 * @param idNumber  - The ID number to retrieve the delimiter from
	 * 
	 * @return The delimiter if present. Otherwise an empty string
	 */
	public static String getDelimiter(String idNumber) {
		return separateDateDelimiterControl(idNumber)[1];
	}
	
	/* Retrieves the control numbers from a personal, coordination or organisation number.
	 * Assumes the ID number is in the format (YY)?YYMMDD[-+]?XXXX. 
	 * @param idNumber  - The ID number to retrieve the control numbers from
	 * 
	 * @return A control number in the format XXXX
	 */
	public static String getControlNumbers(String idNumber) {
		return separateDateDelimiterControl(idNumber)[2];
	}
	
	
	
	/* Takes a birth date and adds a century to it if not present, based on what delimiter it would use in an ID number.
	 * Assumes that the birth date is in the format (YY)?YYMMDD.
	 *  
	 * It is assumed the year implies the latest possible century. If delimiter is +, the year is 
	 * assumed to have occurred at least a century ago.
	 * 
	 * @param birthDate  - The birth date that may or may not have a century in the year.
	 * @param delimiter  - The delimiter that the birth date would use in an ID number
	 * 
	 * @return A birth date in the format of YYYYMMDD
	 */
	private static String addCenturyToDate(String birthDate, String delimiter) {
		int strLength = birthDate.length();
		int decade = Integer.parseInt(birthDate.substring(0, 2));
		
		if(strLength == SHORT_BIRTH_DATE +1 && delimiter.equals("+")) {
			if(decade <= CURRENT_DECADE) {
				birthDate = "19" + birthDate;
			}
			else {
				birthDate = "18" + birthDate;
			}
			//DEFAULTS TO 1922-1900 INSTEAD OF 1822-1800
		}
		else if(strLength == SHORT_BIRTH_DATE || (strLength == SHORT_BIRTH_DATE +1 && delimiter.equals("-"))) {
			if(decade > CURRENT_DECADE) {
				birthDate = "19" + birthDate;
			}
			else {
				birthDate = "20" + birthDate;
			}
		}
		
		return birthDate;
	}
	
	
	/* Retrieves the birth date, delimiter, and control numbers from a personal, coordination or organisation number.
	 * Assumes the ID number is in the format (YY)?YYMMDD[-+]?XXXX.
	 * 
	 * @param idNumber  - The ID number to retrieve birth date, delimiter, and control numbers from
	 * 
	 * @return An array containing the birth date with or without the century, eventual delimiter, and control numbers
	 * 		   of a given ID number.
	 */
	protected static String[] separateDateDelimiterControl(String idNumber) {
		int strLength = idNumber.length();
		String birthDate = "";
		String delimiter = "";
		String controlNums = "";
		
		/* Both conditionals separate the 4 control numbers. Second one separates the delimiter too
		 * Leave 6 or 8 birth numbers respectively.
		 */
		if(strLength == LONG_DATE_NO_DIVIDER || strLength == SHORT_DATE_NO_DIVIDER) {
			birthDate = idNumber.substring(0, strLength -4);
			controlNums = idNumber.substring(strLength -4, strLength);
		}
		else if(strLength == LONG_DATE_WITH_DIVIDER || strLength == SHORT_DATE_WITH_DIVIDER) {
			String[] numSegments = idNumber.split("(?<=(-|\\+))|(?=(-|\\+))");
			birthDate = numSegments[0];
			delimiter = numSegments[1];
			controlNums = numSegments[2];
		}
		
		return new String[]{birthDate, delimiter, controlNums};
	}
	
	
	
	/* Checks whether a given ID number conforms to Luhn's algorithm. 
	 * ID number must be in the format (YY)?YYMMDD[-+]?XXXX. 
	 * 
	 * @param idNumber  - The ID number to calculate Luhn's algorithm on.
	 * 
	 * @return A boolean stating whether the ID number gives a correct result in Luhn's algorithm
	 */
	public static boolean fulfillsLuhnsAlgorithm(String idNumber) {
		if(!isValidFormat(idNumber)) {
			return false;
		}
		
		String noDelimiterID = getBirthDate(idNumber, false) + getControlNumbers(idNumber);
		int multipliedSum = 0;
		
		for(int i = 0; i < noDelimiterID.length(); ++i) {
			int value = Integer.parseInt(String.valueOf(noDelimiterID.charAt(i)));
			
			if(i % 2 == 0) { value = value*2; }
			else { value = value*1; }
			
			multipliedSum += (value/10) + (value % 10);
		}
		
		if(multipliedSum % 10 == 0) {
			return true;
		}
		
		return false;
	}
}
