package tests;

import static org.junit.Assert.*;
import org.junit.*;

import java.io.File;

import validators.PersonalNumberValidator;

public class TestPersonalNumberValidator {

	File testData = null; 
	
	@Test
    public void testFormatWithValidDashDividers() {
		boolean validDashAndYear = PersonalNumberValidator.isValidFormat("19600220-1234");
		boolean validDash = PersonalNumberValidator.isValidFormat("221031-1234");
		
		assertTrue(validDashAndYear);
		assertTrue(validDash);
    }
	
	@Test
    public void testFormatWithValidPlusDividers() {
		boolean validPlusAndYear = PersonalNumberValidator.isValidFormat("19220406+1234");
		boolean validPlus = PersonalNumberValidator.isValidFormat("000312+1234");
		
		assertTrue(validPlusAndYear);
		assertTrue(validPlus);
    }
	
	
	@Test
    public void testFormatWithInvalidPlusDivider() {
        
		boolean invalidPlusWithCentury = PersonalNumberValidator.isValidFormat("20120430+1234");
		boolean invalidPlusWithYear = PersonalNumberValidator.isValidFormat("19230430+1234"); //DEPENDENT ON DATE BEING 2022, 04 BEFORE THE 30TH
		
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
    public void testDateWithValidLeapYear() {
		boolean validLeapYear = PersonalNumberValidator.isValidBirthDate("160229-1234");
		
		assertTrue(validLeapYear);
    }
	
	@Test
    public void testDateWithInvalidLeapYear() {
		boolean invalidLeapYear = PersonalNumberValidator.isValidBirthDate("170229-1234");
		
		assertFalse(invalidLeapYear);
    }
	
	
	@Test
    public void testDateWithValidDateDelimiter() {
		boolean validDate = PersonalNumberValidator.isValidBirthDate("220331-1234");
		
		assertTrue(validDate);
    }
	
	@Test
    public void testDateWithValidDateWithoutDelimiter() {
		boolean validShortDate = PersonalNumberValidator.isValidBirthDate("2201311234");
		boolean validLongDate = PersonalNumberValidator.isValidBirthDate("192201311234");
		
		assertTrue("Personal number with valid date but without delimiter considered an invalid date", validShortDate);
		assertTrue(validLongDate);
    }
	
	
	@Test
    public void testDateWithInvalidBirthDate() {
		boolean validDate = PersonalNumberValidator.isValidBirthDate("20230331-1234");
		
		assertFalse(validDate);
    }
	
	@Test
    public void testDateWithInvalidDay() {
		boolean invalidDay = PersonalNumberValidator.isValidBirthDate("160240-1234");
		
		assertFalse(invalidDay);
    }
	
	@Test
    public void testDateWithInvalidString() {
		boolean invalidString = PersonalNumberValidator.isValidBirthDate("19YY0130-X234");
        
		assertFalse(invalidString);
    }
	
	
	@Test
    public void testLuhnsAlgorithmWithValidPersonalNumber() {
		boolean validPersonalNumber = PersonalNumberValidator.fulfillsLuhnsAlgorithm("201701102384");
        
		assertTrue(validPersonalNumber);
    }
	
	@Test
    public void testLuhnsAlgorithmWithInvalidPersonalNumber() {
		boolean invalidPersonalNumber = PersonalNumberValidator.fulfillsLuhnsAlgorithm("201701272394");
        
		assertFalse(invalidPersonalNumber);
    }

}
