package validators;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.Scanner;

/*
 * A class for evaluating whether an arbitrary number of personal, coordination, or organisation numbers are valid or not.
 */
public class ValidityCheck {

	private static int createdLogs = 0;
	private File inputFile = null;
	private InputStream inputSource = null;
	
	private Scanner input = null;
	
	/*Creates a new PersonalNumberValidator that will scan STDIN for any invalid Swedish
	 * juridical personal numbers and log them
	 * 
	 * @return A new PersonalNumberValidator object
	 */
	public ValidityCheck() {
		this.inputSource = System.in;
		this.input = new Scanner(this.inputSource);
	}
	
	/* Creates a new PersonalNumberValidator that will scan an input file for any invalid Swedish
	 * juridical personal numbers and log them
	 * @param filePath  - The relative or absolute file path of the input file to scan from
	 * 
	 * @return A new PersonalNumberValidator object
	 */
	public ValidityCheck(String filePath) throws FileNotFoundException{
		this.inputFile = new File(filePath);
		this.input = new Scanner(this.inputFile);
	}
	
	
	
	/*
	 * Reads a preset input source until the end and logs any inputs that are not considered valid personal, 
	 * coordination or organisation numbers in a new file.
	 * 
	 * @return None
	 */
	public void validateInput() throws IOException{
		//File log = createOutputLog();
		//log.setWritable(true, false);
		
		while(input.hasNextLine()){
			String idNumber = input.nextLine().strip();
			
			if(idNumber.isBlank()) {
				continue;
			}
			else if(!isValidIdNumber(idNumber)) {
				System.out.println(idNumber);
				//Log in newly created output file
				// log.write()
				// file.close()
				//Log incorrect numbers here? In main? Separate module?
			}
		}
		
		//TODO: Read separation of concerns/
	}
	
	/* Checks whether an ID number is either a valid personal, coordination, or organisation number.
	 * 
	 * @param idNumber  - The ID number that may or may not be valid
	 * 
	 * @return A boolean stating whether a given ID number is a valid personal, coordination, or organisation number. 
	 * 		   Returns false if none of these.
	 */
	public static boolean isValidIdNumber(String idNumber) {
		
		if(PersonalNumberValidator.isValidPersonalNumber(idNumber)) {
			return true;
		}
		else if(CoordinationNumberValidator.isValidCoordinationNumber(idNumber)) {
			return true;
		}
		else if(OrganisationNumberValidator.isValidOrganisationNumber(idNumber)) {
			return true;
		}
		return false;
	}
	
	
	public File createOutputLog() throws IOException{
		String logPath = "./src/tests/testData/invalidLogs/invalidNumbersLog";
		String logName = "invalidIdNumbersLog";
		
		File log;
		try {
			log	= File.createTempFile(logName + ++createdLogs, ".txt", new File(logPath));
		} catch(IOException e) {
			System.out.println("Output log could not be created. Aborting");
			throw e;
		}
		
		return log;
	}
	
	
	
	/*
	 * Closes the input source that is being read for juridical personal numbers
	 * 
	 * @return None
	 */
	public void closeInput() {
		this.input.close();
	}

}
