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
	
	/*
	 * Takes any number of newline-separated Swedish numbers and then logs which, if any, are invalid.
	 * @param args  - The file paths to 
	 * 
	 * @return None
	 */
	public static void main(String[] args) {
		if(args == null || args.length == 0) {
			ValidityCheck validityChecker = new ValidityCheck(System.in);
			validityChecker.validateInput();
			validityChecker.closeInput();
			
		}
		
		for(int i=0; i < args.length; ++i){
			ValidityCheck validityChecker = new ValidityCheck(args[i]);
			validityChecker.validateInput();
			validityChecker.closeInput();
		}
		
		
	}
	
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
		
	}
	
	
	/*
	 * 
	 * @param
	 * 
	 * @eturn 
	 */
	public boolean isValidDate() {
		
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
