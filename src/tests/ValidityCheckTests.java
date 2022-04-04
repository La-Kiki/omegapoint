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
}