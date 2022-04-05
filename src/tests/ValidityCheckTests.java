package tests;

import static org.junit.Assert.*;
import org.junit.*;
import org.hamcrest.*;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;

import validators.ValidityCheck;

public class ValidityCheckTests {
	String invalidFilePath = "/not/a/real/file.txt";
	String validFilePath = new File("src/tests/testData/validPersonalNumbers.txt").getAbsolutePath();
	
	File testFilesDirectory = new File("src/tests/testData/").getAbsoluteFile(); 
	File[] testFiles = new File("src/tests/testData/").listFiles();
	
	@BeforeAll
	public static void setup() {
		//TODO: Setup a validator object for each test after constructor tests
		
	}
	
	@Test
    public void validityCheckConstructorWithInvalidFile() {
		assertThrows(FileNotFoundException.class, () -> new ValidityCheck(invalidFilePath));
    }
	
	@Test
    public void validityCheckConstructorWithValidFile() throws FileNotFoundException{
		ValidityCheck checker = new ValidityCheck(validFilePath);
		
		assertEquals(checker.getClass(), ValidityCheck.class);
    }
	
	@Test
	public void testWithExistingTestFiles() {
		for(File f: testFiles) {
			try {
				if(f.isDirectory()) {
					continue;
				}
				String filePath = f.getAbsolutePath();
				ValidityCheck checker = new ValidityCheck(filePath);
				checker.validateInput();
				
			} catch(FileNotFoundException e) {
				System.out.println("Default test file located at " + f + " could not be accessed. Ignoring test");
			}
			catch(IOException e) {
				System.out.println("Could not create output log file - Check permissions. Ignoring test");
			}
			
		}
		
	}
	
	@Test
	public void testWithAnyTestFiles() {
		
	}
}