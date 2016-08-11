package plavaga.junit.maven;


import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
	@Test
    public void test1Sum()
    {
		App app = new App();
		int result = app.getSum(5,10);
		//Assert.assertEquals(result, 16);
		if(result!=15)
		{
			//fail
			Assert.fail("failed!"); 
		}
		else
			//pass
			System.out.println("Pass : Result -> " + result);
    }
	
	@Test
    public void test2Sum()
    {
		App app = new App();
		int result = app.getSum(6,10);
		//Assert.assertEquals(result, 16);
		if(result!=15)
		{
			//fail
			Assert.fail("failed!"); 
		}
		else
			//pass
			System.out.println("Pass  :  Result -> " + result);
    }
}
