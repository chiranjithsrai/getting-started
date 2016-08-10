package plavaga.junit.maven;


import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
	@Test
    public void testSum()
    {
		App app = new App();
		int result = app.getSum(5,10);
		//Assert.assertEquals(result, 16);
		if(result!=15)
		{
			Assert.fail("failed!"); 
		}
		else
			System.out.println("Pass : Result -> " + result);
    }

}
