package g2.java.api;

import static org.junit.Assert.*;

import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotBrowser;
import org.junit.Test;

public class TeamMentorAPI_Test 
{
	public static SWTWorkbenchBot	bot;	
	
	public TeamMentorAPI_Test()
	{
		bot = new SWTWorkbenchBot();
	}
	
	@Test
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
		//SWTBotBrowser browser2 = bot.browserWithId("TeamMentor");
		
		System.out.println("browser: " + browser);
		System.out.println("id: " + browser.getId());
		System.out.println("url: " + browser.getUrl());
		System.out.println("toolTip: " + browser.getToolTipText());
		System.out.println("text: " + browser.getText());
		//System.out.println("browser: " + browser2);
		
		
	/*	bot.getDisplay().syncExec(new Runnable(){

			@Override
			public void run() 
			{
				List<SWTBotView> views = bot.views();
				assertNotNull(views);
				assertTrue("There were no views in the current UI", views.size() > 0);
				System.out.println("*** CurrentViews (in Test_Default_Views)");
				for(SWTBotView view : views)
				{
					Widget _widget = view.getWidget();
					System.out.println(view.getTitle() + " :" + _widget.toString());
				}

			}});
		*/
		
         
		/*final String htmlSnippet = "<h1>some text</h1>";
		TeamMentorAPI.eclipseAPI.display.syncExec(new Runnable() {
			@Override
			public void run() 
			{
				DefaultPart_WebBrowser webBrowser;
				webBrowser = TeamMentorAPI.show_Html_With_TeamMentor_Banner(htmlSnippet);
				assertNotNull(webBrowser); 
				assertNotNull(webBrowser.browser); 
				String html = webBrowser.browser.getText();
				//assertNotNull(html); 
			}
			
		});
		try 
		{
			this.wait(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(true);
		assertNotNull(webBrowser);
		assertNotNull(webBrowser.browser);
		String html = webBrowser.browser.getText();
		assertNotNull(html);
		assertTrue(html.contains(htmlSnippet));
		*/
	}
}
