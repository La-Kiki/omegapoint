package validators;

import java.util.*;
import java.io.*;

/*
 * A class for determining if a Swedish personal number, coordination number, or organization number is considered valid or not.
 */
public class ValidityCheck {
	File inputFile = null;
	InputStream inputSource = null;
	
	Scanner input = null;
	
	//TODO: Consider 
	
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
		}
		
	}
	
	
	public void validateInput() {
		
		//Log incorrect numbers here? In main? Separate module? 
		//TODO: Read separation of concerns
		
		/*if(!isValidSwedishPersonalNumber()){
			//Log in newly created output file
		}*/
	}
	
	public boolean isValidSwedishPersonalNumber() {
		if(isValidPersonalNumber()) {
			return true;
		}
		else if(isValidCoordinationNumber()) {
			return true;
		}
		else if(isValidOrganisationNumber()) {
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
	public boolean isValidNumberFormat() {
		
		return false;
	}
	
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public boolean isValidBirthDate() {
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public boolean isValidPersonalNumber() {
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public boolean isValidCoordinationNumber() {
			
			return false;
	}
	
	/*
	 * Checks whether a given string conforms to the number format limitations of a Swedish organisation number
	 * @param
	 * 
	 * @eturn 
	 */
	public boolean isValidOrganisationNumberFormat() {
		
		return false;
	}
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public boolean isValidOrganisationDate() {
		
		return false;
	}

	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public boolean isValidOrganisationNumber() {
		
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
