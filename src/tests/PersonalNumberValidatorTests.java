package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import validators.PersonalNumberValidator;

public class PersonalNumberValidatorTests {

	
	@Test
    public void testFormatValidityWithValidPersonalNumber() {
		
		assertTrue(PersonalNumberValidator.isValidFormat("19600220-1234"));
    }
	
	@Test
    public void testFormatValidityWithValidCoordinationNumber() {
		
		assertTrue(PersonalNumberValidator.isValidFormat("19600250-1234"));
	}
	
	@Test
    public void testFormatValidityWithValidOrganisationNumber() {
		
		assertTrue(PersonalNumberValidator.isValidFormat("12600250-1234"));
	}
	
	@Test
    public void testFormatValidityWithInvalidInput() {
        
		assertFalse(PersonalNumberValidator.isValidFormat("hm"));
    }
}
