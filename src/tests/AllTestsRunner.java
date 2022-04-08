package tests;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SuiteDisplayName("Validator tests")
@SelectClasses({TestValidityCheck.class, 
				TestPersonalNumberValidator.class, 
				TestCoordinationNumberValidator.class, 
				TestOrganisationNumberValidator.class
				})
public class AllTestsRunner{

}
