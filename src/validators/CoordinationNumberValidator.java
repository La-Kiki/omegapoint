package validators;

/*
 * A class for determining if a Swedish coordination number is considered valid or not.
 */
public class CoordinationNumberValidator extends PersonalNumberValidator{
	
	/*  Checks if a given coordination number conforms to a correct format, date, and to the Luhn algorithm.
	 * Accepts coordination numbers in the format (YY)?YYMMDD[-+]?XXXX
	 * @param orgNumber  - The coordination number to be validated
	 * 
	 * @return A boolean stating whether the given coordination number is valid or not
	 */
	public static boolean isValidCoordinationNumber(String coordinationNumber) {
		if(isValidFormat(coordinationNumber) && isValidCoordinationNumberDate(coordinationNumber) && fulfillsLuhnsAlgorithm(coordinationNumber)) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidCoordinationNumberDate(String coordinationNumber) {
		String personalNumber = subtract60FromCoordinationNumber(coordinationNumber);
		
		if(PersonalNumberValidator.isValidBirthDate(personalNumber)) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	private static String subtract60FromCoordinationNumber(String coordinationNumber) {
		String birthDate = PersonalNumberValidator.getBirthDate(coordinationNumber, true);
		String controlNumbers = PersonalNumberValidator.getControlNumbers(coordinationNumber);
		
		int actualBirthDay = Integer.parseInt(birthDate.substring(birthDate.length() -2));
		actualBirthDay = actualBirthDay - 60;
		
		String personalNumber = birthDate.substring(0, birthDate.length() -2) + actualBirthDay + controlNumbers;
		
		return personalNumber;
	}
	
	
}
