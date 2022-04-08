package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import validators.CoordinationNumberValidator;

public class TestCoordinationNumberValidator {

	@Test
    public void testFormatWithValidCoordinationNumber() {
		
		assertTrue(CoordinationNumberValidator.isValidFormat("19600270-1234"));
	}
	
	@Test
    public void testDateWithValidBirthDay() {
		boolean isValidDate = CoordinationNumberValidator.isValidBirthDate("196002701234");
		
		assertTrue(isValidDate);
	}
	
	@Test
    public void testValidCoordinationNumber() {
		boolean isValidCoordNum = CoordinationNumberValidator.isValidBirthDate("190910799824");
		
		assertTrue(isValidCoordNum);
	}
}
