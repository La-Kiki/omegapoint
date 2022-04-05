package validators;


public class OrganisationNumberValidator extends PersonalNumberValidator{

	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidOrganisationNumber(String orgNumber) {
		if(isValidFormat(orgNumber) && isValidOrganisationValue(orgNumber) && fulfillsLuhnsAlgorithm(orgNumber)) {
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
	public static boolean isValidOrganisationValue(String orgNumber) {
		if(!PersonalNumberValidator.isValidFormat(orgNumber)) {
			return false;
		}
			String birthDate = PersonalNumberValidator.getBirthDate(orgNumber, true);
			String middleNums = birthDate.substring(birthDate.length()-4, birthDate.length()-2);
			
			int middleValue = Integer.parseInt(middleNums);
			if(middleValue >= 20) {
				return true;
			}
			
		return false;
	}
}
