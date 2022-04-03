package tests;

import static org.junit.Assert.*;
import org.junit.*;
import org.hamcrest.*;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;

import validators.ValidityCheck;

public class ValidityCheckTests {
	String invalidFilePath = "/not/a/real/file.txt";
	String validFilePath = new File("src/tests/validPersonalNumbers.txt").getAbsolutePath();
	
	@BeforeAll
	public static void setup() {
		//TODO: Setup a validator object for each test after constructor tests
		
	}
	
	@Test
    public void validityCheckConstructorWithInvalidFile() {
		assertThrows(FileNotFoundException.class, () -> new ValidityCheck(invalidFilePath));
    }
	
	@Test
    public void validityCheckConstructorWithValidFile() {
		ValidityCheck checker = new ValidityCheck(validFilePath);
		
		assertEquals(checker.getClass(), ValidityCheck.class);
    }
	
	
	
	@Test
    public void testFormatValidityWithValidPersonalNumber() {
		
		assertTrue(ValidityCheck.isValidFormat("19600220-1234"));
    }
	
	@Test
    public void testFormatValidityWithValidCoordinationNumber() {
		
		assertTrue(ValidityCheck.isValidFormat("19600250-1234"));
	}
	
	@Test
    public void testFormatValidityWithValidOrganisationNumber() {
		
		assertTrue(ValidityCheck.isValidFormat("12600250-1234"));
	}
	
	
	
	@Test
    public void testFormatValidityWithInvalidInput() {
        
		assertFalse(ValidityCheck.isValidFormat("hm"));
    }
}