package validators;


/*
 *  A class for determining if a Swedish organization number is considered valid or not.
 */
public class OrganisationNumberValidator extends PersonalNumberValidator{

	private static final int ORG_CENTURY = 16; 
	private static final int MIN_ORG_VALUE = 20;
	/*
	 * Checks if a given organisation number conforms to a correct format, value, and to Luhn's algorithm.
	 * Accepts organisation numbers in the format (YY)?YYMMDD[-+]?XXXX
	 * @param orgNumber  - The organisation number to be validated
	 * 
	 * @return A boolean stating whether the given organisation number is valid or not
	 */
	public static boolean isValidOrganisationNumber(String orgNumber) {
		if(OrganisationNumberValidator.isValidFormat(orgNumber) && isValidOrganisationValue(orgNumber) && fulfillsLuhnsAlgorithm(orgNumber)) {
			return true;
		}
		
		return false;
	}
	
	
	/* Checks whether an organisation number conforms to the format (YY)?YYMMDD[-]?XXXX.
	 * 
	 * @param orgNumber  - The organisation number to be checked
	 * 
	 * @return A boolean stating whether a given organisation number follows a valid format
	 */
	public static boolean isValidFormat(String orgNumber) {
		if(PersonalNumberValidator.isValidFormat(orgNumber)) {
			String delimiter = PersonalNumberValidator.getDelimiter(orgNumber);
			
			if(delimiter.equals("-") || delimiter.equals("")){
				return true;
			}
		}
		return false;
	}
	
	/* Checks whether an organisation number contains only valid number values. This means that the "month" must be a value >= 20, 
	 * and the "century" must be 16, if included.
	 * Organisation number must be in the format (16)?YYMMDD[-+]?XXXX
	 * 
	 * @param orgNumber  - The organisation whose value will be checked
	 * 
	 * @return  A boolean stating whether a given organisation number contains valid number values
	 */
	public static boolean isValidOrganisationValue(String orgNumber) {
		if(!PersonalNumberValidator.isValidFormat(orgNumber)) {
			return false;
		}
		
		String birthDate = separateDateDelimiterControl(orgNumber)[0];  // Design breakdown. Reconsider
		String month = birthDate.substring(birthDate.length()-4, birthDate.length()-2);
		int monthNumbers = Integer.parseInt(month);
		
		if(monthNumbers >= MIN_ORG_VALUE && isValidYearPrepend(birthDate)) {
			return true;
		}
		
		return false;
	}
	
	/* Checks whether an organisation number contains a valid "year" or not. If using YYYY it must begin with 16 to be valid,
	 * if using YY it is always valid.
	 * Organisation number must be in the format (16)?YYMMDD[-+]?XXXX
	 * 
	 * @param orgNumber  - The organisation whose value will be checked
	 * 
	 * @return  A boolean stating whether a given organisation number contains a valid "year" value.
	 */
	private static boolean isValidYearPrepend(String birthDate) {
		if(birthDate.length() == LONG_BIRTH_DATE) {
			int century  = Integer.parseInt(birthDate.substring(0,2));
			
			if(century == ORG_CENTURY) {
				return true;
			}
		}
		else if (birthDate.length() == SHORT_BIRTH_DATE){
			return true;
		}
		
		return false;
	}
}
