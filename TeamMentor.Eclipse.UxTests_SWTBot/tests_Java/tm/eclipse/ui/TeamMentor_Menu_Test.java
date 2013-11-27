package tm.eclipse.ui;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertContains;
import static org.junit.Assert.*;
import static tm.swtbot.helpers.SWTBot_Views.*;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.Test;

import tm.eclipse.ui.views.DefaultPart_WebBrowser;
import tm.swtbot.SWTBot_JUnit;
import tm.swtbot.models.SWTBot_View;

public class TeamMentor_Menu_Test  extends SWTBot_JUnit
{
	@Test
	public void check_Expected_TeamMentor_Menu_Items()
	{
		SWTBotMenu teamMentorMenu = bot.menu("TeamMentor");
		assertNotNull(teamMentorMenu);
		assertNotNull(teamMentorMenu.menu("About TeamMentor Plugin"));		
		/*
		assertNotNull(teamMentorMenu.menu("Open TeamMentor.net"));
		assertNotNull(teamMentorMenu.menu("Open Properties Page"));
		*/
	}
	
	@Test
	public void open_About_TeamMentor_Plugin()
	{			
		//Display.getCurrent().asyncExec(new Runnable() { @Override public void run() 
		//	{
			SWTBot_View aboutView = swtBot_View_Fast("TeamMentor");
			if (aboutView != null)
				aboutView.close();
			bot.menu("TeamMentor").menu("About TeamMentor Plugin").click();
			
			aboutView = swtBot_View("TeamMentor");
			assertNotNull(aboutView);
			assertNotNull(aboutView.viewPart);
			assertTrue(aboutView.viewPart instanceof DefaultPart_WebBrowser);
			final Browser webBrowser =  ((DefaultPart_WebBrowser)aboutView.viewPart).browser;			
			
			display.syncExec(new Runnable() { @Override public void run() 
				{
					String html = webBrowser.getText();
					assertContains("TeamMentor", html);
				}});
				//
				
			
			
			//assertNotNull(html);
			//
			
			//assertTrue("failed to find TM html code", html.contains("aaaa"));
	//		}});
		
		
	//	String browserId = PluginPreferences.getDefaultBrowserId(); 
//		SWTBotBrowser browser = bot.browserWithId(browserId);
//		if(browser != null)
//			bot.clo
	
		
		
		
	}	
}
