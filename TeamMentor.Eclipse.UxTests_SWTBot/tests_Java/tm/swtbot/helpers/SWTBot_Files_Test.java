package tm.swtbot.helpers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tm.swtbot.SWTBot_Helpers;

public class SWTBot_Files_Test 
{
	SWTBot_Helpers helper;	
	
	public SWTBot_Files_Test()
	{
		helper = new SWTBot_Helpers();		
	}
	@Test 
	public void SWTBot_Files_Test_Ctor()
	{	
		assertNotNull(helper);
		assertNotNull(helper.bot);		
	}
}
