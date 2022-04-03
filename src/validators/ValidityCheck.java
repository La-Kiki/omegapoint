package validators;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

/*
 * A class for determining if a Swedish personal number, coordination number, or organization number is considered valid or not.
 */
public class ValidityCheck {
	File inputFile = null;
	InputStream inputSource = null;
	
	Scanner input = null;
	
	static final int CURRENT_YEAR = 2022;
	
	static final int NO_CENTURY_NO_SEPARATOR = 10;
	static final int CENTURY_NO_SEPARATOR = 12;
			
	static final int NO_CENTURY_WITH_SEPARATOR = 11;
	static final int CENTURY_WITH_SEPARATOR = 13;
	
	static final int SHORT_BIRTH_DATE = 6;
	static final int LONG_BIRTH_DATE = 8;
	
	//TODO: Consider using enums instead. Separate module?
	
	
	/*
	 * Creates a new ValidityCheck that will scan an input stream for any invalid Swedish
	 * juridical personal numbers and log them
	 * @param inputStream  - The input stream to scan from
	 * 
	 * @eturn A new ValidityCheck object
	 */
	public ValidityCheck(InputStream inputSource) {
		this.inputSource = inputSource;
		this.input = new Scanner(this.inputSource);
	}
	
	/*
	 * Creates a new ValidityCheck that will scan an input file for any invalid Swedish
	 * juridical personal numbers and log them
	 * @param filePath  - The relative or absolute file path of the input file to scan from
	 * 
	 * @eturn A new ValidityCheck object
	 */
	public ValidityCheck(String filePath) {
		this.inputFile = new File(filePath);
		try {
			this.input = new Scanner(this.inputFile);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			//TODO: Reconsider handling. If no file is found, Scanner will inevitably crash later
			// Call other constructor
		}
		
	}
	
	
	public void validateInput() {
		
		while(input.hasNextLine()){
			String idNumber = input.nextLine().strip();
			
			if(!idNumber.isBlank()) {
				continue;
			}
			else if(!isValidIdNumber(idNumber)) {
				//Log in newly created output file
			}
		}
		
		//Log incorrect numbers here? In main? Separate module? 
		//TODO: Read separation of concerns/
	}
	
	
	public static boolean isValidIdNumber(String idNumber) {
		if(isValidPersonalNumber(idNumber)) {
			return true;
		}
		else if(isValidCoordinationNumber(idNumber)) {
			return true;
		}
		else if(isValidOrganisationNumber(idNumber)) {
			return true;
		}
		
		return false;
	}
	
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean isValidFormat(String idNumber) {
		// idNumber consists only of numbers. YYMMDDXXXX or YYYYMMDDXXXX
		String shortDateNoDivider = "[0-9]{10}";
		String longDateNoDivider = "[1-9]{2}" + shortDateNoDivider;
		
		// idNumber may have a + or - as divider between birth date and control numbers. YYMMDD-XXXX or YYYYMMDD-XXXX
		String shortDateWithDivider = "[0-9]{6}(-|\\+)[0-9]{4}";
		String longDateWithDivider = "[1-9]{2}" + shortDateWithDivider;
		
		if(Pattern.matches(shortDateNoDivider + "|"+ longDateNoDivider, idNumber)) {
			return true;
		}
		else if(Pattern.matches(longDateWithDivider + "|" + shortDateWithDivider, idNumber)) {
			return true;
		}
		
		return false;
	}
	
	
	
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean isValidPersonalNumber(String personalNumber) {
		if(isValidFormat(personalNumber)) {
			if(isValidPersonalNumberDate(personalNumber)) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean isValidPersonalNumberDate(String personalNumber) {
		
		return false;
	}
	
	
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean isValidCoordinationNumber(String coordinationNumber) {
		if(isValidFormat(coordinationNumber)) {
			if(isValidCoordinationNumberDate(coordinationNumber)) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean isValidCoordinationNumberDate(String coordinationNumber) {
		
		return false;
	}
	
	
	

	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean isValidOrganisationNumber(String orgNumber) {
		if(isValidFormat(orgNumber)) {
			if(isValidOrganisationNumberFormat(orgNumber)) {
				if(isValidOrganisationDate(orgNumber)) {
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
	 * @eturn 
	 */
	private static boolean isValidOrganisationNumberFormat(String orgNumber) {
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean isValidOrganisationDate(String orgNumber) {

		
		return false;
	}
	
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public static boolean fulfillsLuhnsAlgorithm(String idNumber) {
		
		return false;
	}
	

	/*
	 * Closes the input source that is being read for juridical personal numbers
	 * 
	 * @return 
	 */
	public void closeInput() {
		this.input.close();
	}
}
