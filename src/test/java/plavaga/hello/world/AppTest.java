package plavaga.hello.world;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
	@Test
    public void testPrintHello()
    {
		App app = new App();
		String text = app.printHello();
		
		if(!(text.length()>0))
		{
			Assert.fail("failed!"); 
		}
		else
			System.out.println("Pass : Result -> " + text);
    }

}
