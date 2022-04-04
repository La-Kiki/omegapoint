package validators;

import java.util.Calendar;

public class OrganisationNumberValidator extends PersonalNumberValidator{

	Calendar calendar = Calendar.getInstance();
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidOrganisationNumber(String orgNumber) {
		if(isValidFormat(orgNumber) && isValidOrgNumberFormat(orgNumber) 
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
	private static boolean isValidOrgNumberFormat(String orgNumber) {
		
		
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
