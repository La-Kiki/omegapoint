package validators;


/*
 *  A class for determining if a Swedish organization number is considered valid or not.
 */
public class OrganisationNumberValidator extends PersonalNumberValidator{

	
	/*
	 * Checks if a given organisation number conforms to a correct format, value, and to the Luhn algorithm.
	 * Accepts organisation numbers in the format (YY)?YYMMDD[-+]?XXXX
	 * @param orgNumber  - The organisation number to be validated
	 * 
	 * @return A boolean stating whether the given organisation number is valid or not
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
		String delimiter = PersonalNumberValidator.getDelimiter(orgNumber);
		
		String middleNums = birthDate.substring(birthDate.length()-4, birthDate.length()-2);
		int middleValue = Integer.parseInt(middleNums);
		if(middleValue >= 20 && (delimiter.equals("-") || delimiter.equals(""))) {
			return true;
		}
			
		return false;
	}
}
