package validators;

import java.util.*;
import java.util.regex.Pattern;

/*
 * A class for determining if a Swedish personal number, coordination number, or organization number is considered valid or not.
 */
public class PersonalNumberValidator {

	private static Calendar calendar = Calendar.getInstance();
	
	private static final int CURRENT_YEAR = 2022;
	private static final int CURRENT_DECADE = 22;
	
	private static final int SHORT_DATE_NO_DIVIDER = 10;
	private static final int LONG_DATE_NO_DIVIDER = 12;
			
	private static final int SHORT_DATE_WITH_DIVIDER = 11;
	private static final int LONG_DATE_WITH_DIVIDER = 13;
	
	private static final int SHORT_BIRTH_DATE = 6;
	private static final int LONG_BIRTH_DATE = 8;
	
	//TODO: Consider using enums instead. Separate module?
	
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidPersonalNumber(String personalNumber) {
		if(isValidFormat(personalNumber)) {
			if(isValidDate(personalNumber)) {
				if(fulfillsLuhnsAlgorithm(personalNumber)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidFormat(String idNumber) {
		// idNumber consists only of numbers. YYMMDDXXXX or YYYYMMDDXXXX
		String shortDateNoDivider = "[0-9]{10}";
		String longDateNoDivider = "[1-9]{2}" + shortDateNoDivider;
		
		/* idNumber may have a + or - as divider between birth date and control numbers. 
		 * YYMMDD-XXXX, YYYYMMDD-XXXX, YYMMDD+XXXX or YYYYMMDD+XXXX
		 */
		String shortDateWithDivider = "[0-9]{6}(-|\\+)[0-9]{4}";
		String longDateWithDivider = "[1-9]{2}" + shortDateWithDivider;
		
		if(Pattern.matches(shortDateNoDivider + "|"+ longDateNoDivider, idNumber)) {
			return true;
		}
		else if(Pattern.matches(longDateWithDivider + "|" + shortDateWithDivider, idNumber)) {
			return true;
		}
		
		return false;
	}
	
	
	
	
	/*
	 *  Assumes that the given ID number conforms to one of the following formats: 
	 *  YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidDate(String personalNumber) {
		String birthDate = standardizeDateFormat(personalNumber);
		
		int century = Integer.parseInt(personalNumber.substring(0, 3));
		int month = Integer.parseInt(birthDate.substring(4, 5));
		int day = Integer.parseInt(birthDate.substring(6, 7));
		
		calendar.setLenient(false);
		
		if(century <= 2022) {
			return true;
		}
		
		return false;
	}
	
	/*
	 *  . Assumes that the given ID number conforms to one of the following formats: 
	 *  YYMMDD-XXXX, YYMMDD+XXXX, YYYYMMDD-XXXX, YYYYMMDD+XXXX, YYMMDDXXXX, YYYYMMDDXXXX
	 * @param
	 * 
	 * @return 
	 */
	private static String standardizeDateFormat(String personalNumber) {
		int numLength = personalNumber.length();
		
		/* All conditionals discard the 4 control numbers. 
		 * Leaves 6 or 8 birth numbers, with eventual divider + or -
		 */
		if(numLength == LONG_DATE_NO_DIVIDER) {
			personalNumber = personalNumber.substring(0, LONG_BIRTH_DATE -1);
		}
		else if(numLength == SHORT_DATE_NO_DIVIDER) {
			personalNumber = personalNumber.substring(0, SHORT_BIRTH_DATE -1);
			addCenturyToDate(personalNumber);
		}
		else if(numLength == SHORT_DATE_WITH_DIVIDER || numLength == LONG_DATE_WITH_DIVIDER){
			personalNumber = personalNumber.split("?<=(-|\\+)")[0];
			addCenturyToDate(personalNumber);
		}
		
		return personalNumber;
	}
	
	/*
	 *  . Assumes that the given ID number conforms to one of the following formats: 
	 *  YYMMDD-, YYMMDD+, YYMMDD
	 * @param
	 * 
	 * @return 
	 */
	private static String addCenturyToDate(String personalNumber) {
		int numLength = personalNumber.length();
		int decade = Integer.parseInt(personalNumber.substring(0, 1));
		
		if(numLength == SHORT_BIRTH_DATE +1 && personalNumber.contains("\\+")) {
			if(decade <= CURRENT_DECADE) {
				personalNumber = "19" + personalNumber;
			}
			else {
				personalNumber = "18" + personalNumber;
			}
			//Clear gap between 1822-1800 - dates never accessed...
			//TODO: Consider delaying addition of century
		}
		else if(numLength == SHORT_BIRTH_DATE || (numLength == SHORT_BIRTH_DATE +1 && personalNumber.contains("-"))) {
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
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean fulfillsLuhnsAlgorithm(String idNumber) {
		
		return false;
	}
	
}
