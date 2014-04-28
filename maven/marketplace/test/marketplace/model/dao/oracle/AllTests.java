package marketplace.model.dao.oracle;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit tests for marketplace
 * 
 * @author Roman_Ten
 * 
 */
public final class AllTests {
	private AllTests() {

	}

	/**
	 * Composite of Tests.
	 * 
	 * @return TestSuite is suite of test.
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for marketplace.model.dao.oracle");
		// $JUnit-BEGIN$
		/*suite.addTestSuite(OracleBidDAOTest.class);*/
		suite.addTestSuite(OracleItemDAOTest.class);
		// $JUnit-END$
		return suite;
	}

}
