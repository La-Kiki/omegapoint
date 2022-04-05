package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import validators.OrganisationNumberValidator;

public class OrganisationNumberValidatorTests {

	@Test
    public void testFormatWithValidOrganisationNumber() {
		boolean isValidFormat = OrganisationNumberValidator.isValidFormat("16602050-1234");
		
		assertTrue(isValidFormat);
	}
	
	@Test
    public void testValueWithValidOrganisationNumber() {
		boolean isValidFormat = OrganisationNumberValidator.isValidOrganisationValue("16556601-6399");
		
		assertTrue(isValidFormat);
	}
	
	@Test
    public void testValueWithInvalidOrganisationNumber() {
		boolean isInvalidFormat = OrganisationNumberValidator.isValidOrganisationValue("12556601-6399");
		
		assertFalse(isInvalidFormat);
	}
	
	@Test
    public void testValidOrganisationNumber() {
		boolean isValidFormat = OrganisationNumberValidator.isValidOrganisationNumber("262000-1111");
		
		assertTrue(isValidFormat);
	}
}
