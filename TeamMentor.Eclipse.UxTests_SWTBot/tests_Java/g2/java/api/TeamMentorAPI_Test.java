package g2.java.api;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotBrowser;
import org.junit.Ignore;
import org.junit.Test;

public class TeamMentorAPI_Test 
{
	public static SWTWorkbenchBot	bot;	
	
	public TeamMentorAPI_Test()
	{
		bot = new SWTWorkbenchBot();
	}
	
	@Test
	@Ignore
	public void show_Html_With_TeamMentor_Banner()
	{
		SWTBotView _view = null;
		try
		{
			_view=  bot.viewByTitle("TeamMentor");
		}
		catch(WidgetNotFoundException ex) {}
		if (_view == null)
		{
			bot.menu("TeamMentor")
			.menu("Open TeamMentor View").click();
		}
		_view =  bot.viewByTitle("TeamMentor");
		_view.setFocus();
		
		SWTBotBrowser browser = //bot.browser();
				bot.browser(1);
		
		System.out.println("browser: " + browser);
		System.out.println("id: " + browser.getId());
		System.out.println("url: " + browser.getUrl());
		System.out.println("toolTip: " + browser.getToolTipText());
		System.out.println("text: " + browser.getText());		
	}
}
