package swedishValidityCheck;

import java.util.*;
import java.io.*;

/*
 * A class for determining if a Swedish personal number, coordination number, or organization number is considered valid or not.
 */
public class ValidityCheck {
	File inputFile = null;
	Scanner input = null;
	
	/*
	 * Takes any number of newline-separated Swedish numbers and then logs which, if any, are invalid.
	 * @param args[0]  - The file path to 
	 * 
	 * @return None
	 */
	public static void main(String[] args) {

	}
	
	public ValidityCheck(String filePath) throws FileNotFoundException {
		// Check if good standard to split or run into new File directly in Scanner
		this.inputFile = new File(filePath);
		this.input = new Scanner(this.inputFile);
	}
	
	
	public boolean isValidDate() {
		
		return false;
	}
	
	
	public boolean isValidNumberFormat() {
		
		return false;
	}
	
	public boolean isValidPersonalNumber() {
		
		return false;
	}
	
	public boolean isValidCoordinationNumber() {
			
			return false;
	}

	public boolean isValidOrganisationNumber() {
		
		return false;
	}

	public void closeInput() {
		this.input.close();
	}
}
