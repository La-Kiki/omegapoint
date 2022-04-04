package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import validators.CoordinationNumberValidator;

public class CoordinationNumberValidatorTests {

	@Test
    public void testFormatValidityWithValidCoordinationNumber() {
		
		assertTrue(CoordinationNumberValidator.isValidFormat("19600250-1234"));
	}
}
