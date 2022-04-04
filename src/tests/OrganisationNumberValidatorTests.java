package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import validators.OrganisationNumberValidator;

public class OrganisationNumberValidatorTests {

	@Test
    public void testFormatValidityWithValidOrganisationNumber() {
		
		assertTrue(OrganisationNumberValidator.isValidFormat("12600250-1234"));
	}
}
