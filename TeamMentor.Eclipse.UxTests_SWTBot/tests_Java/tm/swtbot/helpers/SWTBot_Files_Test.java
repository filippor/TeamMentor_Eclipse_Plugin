package tm.swtbot.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tm.swtbot.SWTBot_Helpers;

public class SWTBot_Files_Test 
{
	SWTBot_Helpers helper;
	SWTBot_Files   files;
	
	public SWTBot_Files_Test()
	{
		helper = new SWTBot_Helpers();
		files  = helper.files;
	}
	@Test 
	public void SWTBot_Files_Test_Ctor()
	{	
		assertNotNull(helper);
		assertNotNull(helper.bot);
		assertNotNull(files);
		assertNotNull(files.bot);
		assertEquals (files.bot, helper.bot);
	}
}
