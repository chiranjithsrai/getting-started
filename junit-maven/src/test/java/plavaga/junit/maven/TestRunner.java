/**
 * 
 */
package plavaga.junit.maven;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author chiranjithsrai
 *
 */
public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(JunitTestSuite.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.wasSuccessful())
			System.out.println("Test Pass");
		else
			System.out.println("Test Fail");
	}
}
