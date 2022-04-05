package tests;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import validators.ValidityCheck;

public class ValidityCheckTestsArbitraryData {

	File testFilesDirectory = new File("src/tests/testData/"); 
	File[] testFiles = testFilesDirectory.listFiles();
	
	File expectedLogOutputsDir = new File("src/tests/testData/expectedLogOutput/");
	File[] expectedLogOutputs = expectedLogOutputsDir.listFiles();
	
	@Test
	public void testWithAnyTestFiles() {
		for(File f: testFiles) {
			if(f.isDirectory()) {
				continue;
			}
			
			try {
				ValidityCheck checker = new ValidityCheck(f.toString());
				String outputLogPath = checker.validateInput();
				String outputLogName = new File(outputLogPath).getName();
				
				BufferedReader logReader = new BufferedReader(new FileReader(outputLogPath));
				
				String expectedOutputpath = expectedLogOutputsDir.toString() + "/" + outputLogName;
				BufferedReader expectedOutputReader = new BufferedReader(new FileReader(expectedOutputpath));
				
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
