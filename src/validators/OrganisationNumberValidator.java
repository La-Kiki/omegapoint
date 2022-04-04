package validators;


public class OrganisationNumberValidator extends PersonalNumberValidator{

	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidOrganisationNumber(String orgNumber) {
		if(isValidFormat(orgNumber) && isValidOrgNumberDate(orgNumber) 
				&& isValidOrganisationValue(orgNumber) && fulfillsLuhnsAlgorithm(orgNumber)) {
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * Checks whether a given string conforms to the number format limitations of a Swedish organisation number.
	 * @param
	 * 
	 * @return 
	 */
	private static boolean isValidOrgNumberDate(String orgNumber) {
		
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidOrganisationValue(String orgNumber) {

		
		return false;
	}
}
