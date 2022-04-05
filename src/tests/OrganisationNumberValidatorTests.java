package tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import validators.OrganisationNumberValidator;

public class OrganisationNumberValidatorTests {

	@Test
    public void testFormatyWithValidOrganisationNumber() {
		boolean isValidFormat = OrganisationNumberValidator.isValidFormat("16602050-1234");
		
		assertTrue(isValidFormat);
	}
	
	@Test
    public void testValidOrganisationNumberValue() {
		boolean isValidFormat = OrganisationNumberValidator.isValidOrganisationValue("16556601-6399");
		
		assertTrue(isValidFormat);
	}
	
	@Test
    public void testValidOrganisationNumber() {
		boolean isValidFormat = OrganisationNumberValidator.fulfillsLuhnsAlgorithm("262000-1111");
		
		assertTrue(isValidFormat);
	}
}
