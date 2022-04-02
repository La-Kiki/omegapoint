package tests;

import static org.junit.Assert.*;
import org.junit.*;
import org.hamcrest.*;

import java.io.*;

import validators.ValidityCheck;

public class ValidityCheckTests {

	
	@Test
    public void validityCheckConstructorWithInvalidFile() {
		assertThrows(FileNotFoundException.class, () -> new ValidityCheck("/not/a/real/file.txt"));
    }
	
	@Test
    public void validityCheckConstructorWithValidFile() {
		String validInputFile = new File("src/tests/validPersonalNumbers.txt").getAbsolutePath();
		ValidityCheck checker = new ValidityCheck(validInputFile);
		
		assertEquals(checker.getClass(), ValidityCheck.class);
    }
	
	@BeforeEvery
	public void setup() {
		//TODO: Setup a validator object for each test after constructor tests
	}
	
	@Test
    public void testFormatValidityWithValidInput() {
		String validInputFile = new File("src/tests/validPersonalNumbers.txt").getAbsolutePath();
		ValidityCheck checker = new ValidityCheck(validInputFile);
		
		assertTrue(checker.isValidNumberFormat());
        
    }
	
	@Test
    public void testFormatValidityWithInvalidInput() {
        
    }
}