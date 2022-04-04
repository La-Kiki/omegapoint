package validators;


public class CoordinationNumberValidator extends PersonalNumberValidator{
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidCoordinationNumber(String coordinationNumber) {
		if(isValidFormat(coordinationNumber) && isValidCoordinationNumberDate(coordinationNumber) 
				&& fulfillsLuhnsAlgorithm(subtract60FromCoordinationNumber(coordinationNumber))) {
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
		System.out.println(personalNumber);
		
		if(PersonalNumberValidator.isValidDate(personalNumber)) {
			System.out.println("true");
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
