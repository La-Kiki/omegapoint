package validators;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Scanner;

/*
 * A class for evaluating whether an arbitrary number of personal, coordination, or organisation numbers are valid or not.
 */
public class ValidityCheck {
	// Currently unchangeable. Can be adjusted in the future with a set-method or adding constructor
	private final String outputLogDirectory = "src/outputLogs/";
	private String outputLogPath = outputLogDirectory + "invalidLog_";
	
	// The file where the output log will be written to
	BufferedWriter logWriter = null;
	
	// Source may either be an existing file or an input stream
	private File inputFile = null;
	private InputStream inputSource = null;
	
	private Scanner input = null;
	
	/*Creates a new PersonalNumberValidator that will scan STDIN for any invalid Swedish
	 * ID numbers and log them in a predetermined directory
	 * 
	 * @return A new PersonalNumberValidator object
	 */
	public ValidityCheck() throws IOException {
		this.inputSource = System.in;
		this.input = new Scanner(this.inputSource);
		
		initializeLogDirAndFile();
	}
	
	/* Creates a new PersonalNumberValidator that will scan an input file for any invalid Swedish
	 * ID numbers and log them in a predetermined directory
	 * @param filePath  - The relative or absolute file path of the input file to scan from
	 * 
	 * @return A new PersonalNumberValidator object
	 */
	public ValidityCheck(String filePath) throws FileNotFoundException, IOException{
		this.inputFile = new File(filePath);
		this.input = new Scanner(this.inputFile);
		
		initializeLogDirAndFile();
		
	}
	
	/*
	 * Creates a log file name in a predetermined directory for a given input source's output log, and
	 * initializes the object that will write to the log.
	 * 
	 * @return None
	 */
	private void initializeLogDirAndFile() throws IOException{
		createOutputLogPath();
		Files.createDirectories(Paths.get(this.outputLogDirectory));
		this.logWriter = new BufferedWriter(new FileWriter(outputLogPath));
	}
	
	/*
	 * Creates a file name in a predetermined directory for a given input source's output log.
	 * 
	 * @return None
	 */
	private void createOutputLogPath() throws IOException{
		if(inputFile != null) {
			this.outputLogPath = this.outputLogPath + inputFile.getName();
		}
		else {
			this.outputLogPath = this.outputLogPath + "STDIN";
		}
	}
	
	
	/*
	 * Reads a preset input source until the end and logs any inputs that are not considered valid personal, 
	 * coordination or organisation numbers into a new file.
	 * 
	 * @return The file path to a newly created output log with invalid ID numbers
	 */
	public String validateInput() throws IOException{
		while(input.hasNextLine()){
			String idNumber = input.nextLine().strip();
			
			if(idNumber.isBlank()) {
				continue;
			}
			else if(!isValidIdNumber(idNumber)) {
				writeToLog(idNumber);
			}
		}
		this.logWriter.flush();
		
		return outputLogPath;
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
	
	/*
	 * Writes an ID number to the predetermined log file.
	 * 
	 * @return None
	 */
	private void writeToLog(String idNumber) throws IOException {
		this.logWriter.write(idNumber);
		this.logWriter.newLine();
	}
	
	/*
	 * Closes the input source that is being read for ID numbers, and the output source that is being written to.
	 * Should only be called by end of ValidityChecker object's lifespan, in order to not leave any resources open.
	 * 
	 * @return None
	 */
	public void closeResources() throws IOException{
		this.input.close();
		this.logWriter.close();
	}
}
