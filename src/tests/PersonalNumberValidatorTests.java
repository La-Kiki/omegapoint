package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;

import validators.PersonalNumberValidator;

public class PersonalNumberValidatorTests {

	File testData = null; 
	
	@Test
    public void testFormatWithValidDividers() {
		boolean validDashAndYear = PersonalNumberValidator.isValidFormat("19600220-1234");
		boolean validPlusAndYear = PersonalNumberValidator.isValidFormat("19220331+1234");
		boolean validPlus = PersonalNumberValidator.isValidFormat("000312+1234");
		boolean validDash = PersonalNumberValidator.isValidFormat("221031-1234");
		
		assertTrue(validDashAndYear);
		assertTrue(validPlusAndYear);
		assertTrue(validPlus);
		assertTrue(validDash);
    }
	
	@Test
    public void testFormatWithInvalidDividers() {
        
		boolean invalidPlusWithCentury = PersonalNumberValidator.isValidFormat("20120430+1234");
		boolean invalidPlusWithYear = PersonalNumberValidator.isValidFormat("19230430+1234");
		
		assertFalse(invalidPlusWithCentury);
		assertFalse(invalidPlusWithYear);
    }
	
	@Test
    public void testFormatWithValidLengths() {
		boolean validDecade = PersonalNumberValidator.isValidFormat("6002201234");
		boolean validYear = PersonalNumberValidator.isValidFormat("202205151234");
		boolean validLeapYear = PersonalNumberValidator.isValidFormat("0602291234");
		
		assertTrue(validDecade);
		assertTrue(validYear);
		assertTrue(validLeapYear);
    }
	
	@Test
    public void testFormatWithInvalidLengths() {
		boolean invalidYearLength = PersonalNumberValidator.isValidFormat("199990130-1234");
		boolean shortMissingControlNums = PersonalNumberValidator.isValidFormat("990130");
		boolean longMissingControlNums = PersonalNumberValidator.isValidFormat("18980130");
        
		assertFalse(invalidYearLength);
		assertFalse(shortMissingControlNums);
		assertFalse(longMissingControlNums);
    }
	
	@Test
    public void testFormatWithInvalidString() {
		boolean invalidString = PersonalNumberValidator.isValidFormat("199990130-XXXX");
        
		assertFalse(invalidString);
    }
	
	
	
	@Test
    public void testDateWithValidYear() {
		boolean validLeapYear = PersonalNumberValidator.isValidDate("160229-1234");
		
		assertTrue(validLeapYear);
    }
	
	@Test
    public void testDateWithValidDate() {
		boolean validDate = PersonalNumberValidator.isValidDate("220531-1234");
		
		assertTrue(validDate);
    }
	
	@Test
    public void testDateWithInvalidDay() {
		boolean invalidDay = PersonalNumberValidator.isValidDate("160240-1234");
		
		assertFalse(invalidDay);
    }
	
	@Test
    public void testDateWithInvalidString() {
		boolean invalidString = PersonalNumberValidator.isValidDate("19YY0130-X234");
        
		assertFalse(invalidString);
    }

}
