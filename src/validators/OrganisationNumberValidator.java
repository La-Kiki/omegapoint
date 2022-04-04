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
		if(isValidFormat(orgNumber) && isValidOrganisationNumberFormat(orgNumber)) {
			if(isValidOrganisationDate(orgNumber)) {
				if(fulfillsLuhnsAlgorithm(orgNumber)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	/*
	 * Checks whether a given string conforms to the number format limitations of a Swedish organisation number.
	 * @param
	 * 
	 * @return 
	 */
	private static boolean isValidOrganisationNumberFormat(String orgNumber) {
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
	 */
	public static boolean isValidOrganisationDate(String orgNumber) {

		
		return false;
	}
}
