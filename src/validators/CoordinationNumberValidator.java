package validators;

import java.util.Calendar;

public class CoordinationNumberValidator extends PersonalNumberValidator{
	
	Calendar calendar = Calendar.getInstance();
	
	/*
	 * 
	 * @param
	 * 
	 * @return 
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
		
		return false;
	}
	
}
