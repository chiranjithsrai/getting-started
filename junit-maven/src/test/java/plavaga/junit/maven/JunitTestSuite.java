/**
 * 
 */
package plavaga.junit.maven;

/**
 * @author chiranjithsrai
 *
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			TestEmployeeDetails.class, 
			JunitAnnotation.class
		})

public class JunitTestSuite {
}