package tm.eclipse.ui;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertContains;
import static org.junit.Assert.*;
import static tm.swtbot.helpers.SWTBot_Views.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotBrowser;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.Test;

import tm.eclipse.ui.views.DefaultPart_WebBrowser;
import tm.swtbot.models.SWTBot_View;

public class TeamMentor_Menu_Test 
{
	@Test
	public void check_Expected_TeamMentor_Menu_Items()
	{
		
		SWTWorkbenchBot bot = new SWTWorkbenchBot();
		SWTBotMenu teamMentorMenu = bot.menu("TeamMentor");				
		assertNotNull(teamMentorMenu.menu("About TeamMentor Plugin"));
		assertNotNull(teamMentorMenu.menu("Open TeamMentor Website"));
		//assertNotNull(teamMentorMenu.menu("Open Properties Page"));		
	}
	
	@Test
	public void about_TeamMentor_Plugin()
	{			

		SWTBot_View aboutView = swtBot_View_Fast("TeamMentor");
		if (aboutView != null)
			aboutView.close();
		bot.menu("TeamMentor").menu("About TeamMentor Plugin").click();
		
		aboutView = swtBot_View("TeamMentor");
		assertNotNull(aboutView);
		assertNotNull(aboutView.viewPart);
		assertTrue(aboutView.viewPart instanceof DefaultPart_WebBrowser);
		final Browser webBrowser =  ((DefaultPart_WebBrowser)aboutView.viewPart).browser;			
		assertNotNull(webBrowser);
		
		SWTBotBrowser swtBotBrowser = new SWTBotBrowser(webBrowser);
		swtBotBrowser.waitForPageLoaded();
		String html = swtBotBrowser.getText();
		assertContains("TeamMentor", html);		
		aboutView.close();
		aboutView = swtBot_View_Fast("TeamMentor");
		assertNull(aboutView);
	}
	
	@Test
	public void open_TeamMentor_Website()
	{
		try 
		{
			new URL("http://teammentor.net").openConnection().getInputStream();
			fail("here");
		} catch (MalformedURLException e) 
		{
			fail("in MalformedURLException");
		} catch (IOException e) 
		{
			fail("in IOException: " + e.getMessage());
		}
		//bot.menu("TeamMentor").menu("Open TeamMentor Website").click();
		//SWTBot_View teamMentorNet = swtBot_View_Fast("http://teammentor.net");
		//assertNotNull(teamMentorNet);
	}
}
