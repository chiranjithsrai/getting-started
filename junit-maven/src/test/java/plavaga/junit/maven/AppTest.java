package plavaga.junit.maven;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Unit test for simple App.
 */

public class AppTest {

	private int fValue1;
	private int fValue2;

	@Test
	public void test1Sum() {
		App app = new App();
		int result = app.getSum(fValue1, fValue2);
		// Assert.assertEquals(result, 16);
		if (result != 15) {
			// test fail
			Assert.fail("failed!");
		} else
			// pass
			System.out.println("1 Pass : Result -> " + result);
	}

	@Test
	public void test2Sum() {
		App app = new App();
		int result = app.getSum(fValue1, fValue2);
		// Assert.assertEquals(result, 16);
		if (result != 16) {
			// fail
			Assert.fail("failed!");
		} else
			// pass
			System.out.println("2 Pass  :  Result -> " + result);
	}

	@Before
	public void setUp() {
		System.out.println("SETUP");
		fValue1 = 10;
		fValue2 = 5;
	}

	public void testPrintHello() {
		Result result = JUnitCore.runClasses(App.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
		// Assert.assertEquals(result, 16);
	}

}
