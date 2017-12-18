package tests;

import junit.framework.TestSuite;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({IntegrationDB.class, IntegrationTest.class})
public class AllTests {


}