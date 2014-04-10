package $com.epam.archetype;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DAOFactoryTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DAOFactoryTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DAOFactoryTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void DAOFactoryApp()
    {
        assertTrue( true );
    }
}
