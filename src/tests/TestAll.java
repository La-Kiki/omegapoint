package tests;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestValidityCheck.class, 
				TestPersonalNumberValidator.class, 
				TestCoordinationNumberValidator.class, 
				TestOrganisationNumberValidator.class
				})
public class TestAll{

}
