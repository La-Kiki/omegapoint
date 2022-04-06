package validators;

/*
 * A class for determining if a Swedish coordination number is considered valid or not.
 */
public class CoordinationNumberValidator extends PersonalNumberValidator{
	
	private static final int COORD_NUM_OFFSET = 60;
	
	/* Checks if a given coordination number conforms to a correct format, date, and to Luhn's algorithm.
	 * Accepts coordination numbers in the format (YY)?YYMMDD[-+]?XXXX.
	 * 
	 * @param coordinationNumber  - The coordination number to be validated
	 * 
	 * @return A boolean stating whether the given coordination number is valid or not
	 */
	public static boolean isValidCoordinationNumber(String coordinationNumber) {
		if(isValidFormat(coordinationNumber) && CoordinationNumberValidator.isValidBirthDate(coordinationNumber) && fulfillsLuhnsAlgorithm(coordinationNumber)) {
			return true;
		}
		
		return false;
	}
	
	/* Checks whether a coordination number contains a valid birth date. Coordination number 
	 * must be in the format (YY)?YYMMDD[-+]?XXXX.
	 * 
	 * @param coordinationNumber  - The coordination number to check for a valid birth date
	 * 
	 * @return A boolean stating whether the coordination number contains a valid birth date
	 */
	public static boolean isValidBirthDate(String coordinationNumber) {
		String personalNumber = subtractOffsetFromCoordinationNumber(coordinationNumber);
		
		if(PersonalNumberValidator.isValidBirthDate(personalNumber)) {
			return true;
		}
		
		return false;
	}
	
	/* Subtracts the offset between from a coordination number's birth day to give it the same form as a personal number.
	 * Assumes the coordination number is in the format (YY)?YYMMDD[-+]?XXXX.
	 * 
	 * @param coordinationNumber  - A coordination number whose offset will be subtracted
	 * 
	 * @return A string of a coordinate number with the offset subtracted
	 */
	private static String subtractOffsetFromCoordinationNumber(String coordinationNumber) {
		String birthDate = getBirthDate(coordinationNumber, true);
		String controlNumbers = getControlNumbers(coordinationNumber);
		
		int actualBirthDay = Integer.parseInt(birthDate.substring(birthDate.length() -2)) - COORD_NUM_OFFSET;
		
		String personalNumber = birthDate.substring(0, birthDate.length() -2) + actualBirthDay + controlNumbers;
		
		return personalNumber;
	}
	
	
}
