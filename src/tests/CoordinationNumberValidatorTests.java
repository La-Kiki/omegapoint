package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import validators.CoordinationNumberValidator;

public class CoordinationNumberValidatorTests {

	@Test
    public void testFormatWithValidCoordinationNumber() {
		
		assertTrue(CoordinationNumberValidator.isValidFormat("19600270-1234"));
	}
	
	@Test
    public void testDateWithValidBirthDay() {
		boolean isValidDate = CoordinationNumberValidator.isValidCoordinationNumberDate("196002701234");
		
		assertTrue(isValidDate);
	}
	
	@Test
    public void testValidCoordinationNumber() {
		boolean isValidCoordNum = CoordinationNumberValidator.isValidCoordinationNumber("190910799824");
		
		assertTrue(isValidCoordNum);
	}
}
