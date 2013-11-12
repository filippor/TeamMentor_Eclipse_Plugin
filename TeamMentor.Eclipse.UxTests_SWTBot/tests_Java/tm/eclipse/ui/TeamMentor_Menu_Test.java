package tm.eclipse.ui;

import static org.junit.Assert.*;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotBrowser;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.Test;

import tm.swtbot.SWTBot_JUnit;

public class TeamMentor_Menu_Test  extends SWTBot_JUnit
{
	@Test
	public void check_Expected_TeamMentor_Menu_Items()
	{
		SWTBotMenu teamMentorMenu = bot.menu("TeamMentor");
		assertNotNull(teamMentorMenu);
		assertNotNull(teamMentorMenu.menu("Open TeamMentor View"));
		assertNotNull(teamMentorMenu.menu("Open TeamMentor.net"));
		assertNotNull(teamMentorMenu.menu("Open Properties Page"));
	}
	
	@Test
	public void open_TeamMentor_View()
	{
		String browserId = PluginPreferences.getDefaultBrowserId(); 
//		SWTBotBrowser browser = bot.browserWithId(browserId);
//		if(browser != null)
//			bot.clo
	
		
		
		
	}	
}
