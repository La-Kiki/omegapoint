package tests;

import static org.junit.Assert.*;
import org.junit.*;

import validators.ValidityCheck;

import java.io.*;

public class TestValidityCheck {
	String invalidFilePath = "/not/a/real/file.txt";
	String validFilePath = new File("./src/tests/testData/validPersonalNumbers.txt").toString();
	
	File testFilesDirectory = new File("./src/tests/testData/"); 
	File[] testFiles = testFilesDirectory.listFiles();
	
	File expectedLogOutputsDir = new File("./src/tests/testData/expectedLogOutput/");
	File[] expectedLogOutputs = expectedLogOutputsDir.listFiles();

	
	@Test
    public void validityCheckConstructorWithInvalidFile() {
		assertThrows(FileNotFoundException.class, () -> new ValidityCheck(invalidFilePath));
    }
	
	@Test
    public void validityCheckConstructorWithValidFile() throws FileNotFoundException, IOException{
		ValidityCheck checker = new ValidityCheck(validFilePath);
		
		assertEquals(checker.getClass(), ValidityCheck.class);
    }
	
	@Test
	public void testWithExistingTestFiles() {
		
		for(File f: testFiles) {
			if(f.isDirectory()) {
				continue;
			}
			
			try {
				ValidityCheck checker = new ValidityCheck(f.toString());
				String outputLogPath = checker.validateInput();
				String outputLogName = new File(outputLogPath).getName();
				
				BufferedReader logReader = new BufferedReader(new FileReader(outputLogPath));
				
				String expectedOutputPath = expectedLogOutputsDir.toString() + "/" + outputLogName;
				BufferedReader expectedOutputReader = new BufferedReader(new FileReader(expectedOutputPath));
				
				String loggedOutput = "";
				String expectedOutput = "";
				
				while((loggedOutput = logReader.readLine()) != null && (expectedOutput = expectedOutputReader.readLine()) != null) {
					assertTrue(loggedOutput.equals(expectedOutput));
				}
				
				logReader.close();
				expectedOutputReader.close();
				
			} catch(FileNotFoundException e) {
				System.out.println("Either default test file located at " + f + " could not be accessed, or the output path is wrong. Ignoring test");
			}
			catch(IOException e) {
				System.out.println("Could not create output log file - Check permissions. Ignoring test");
			}
			
		}
	}
	
	
}