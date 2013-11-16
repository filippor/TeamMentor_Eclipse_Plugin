package tm.eclipse.handlers;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertContains;
import static org.junit.Assert.*;
import static tm.swtbot.helpers.SWTBot_Views.swtBot_View_Fast;

import org.eclipse.swt.browser.Browser;
import org.junit.Test;

import tm.eclipse.ui.views.DefaultPart_WebBrowser;
import tm.swtbot.SWTBot_JUnit;
import tm.swtbot.models.SWTBot_View;

public class ShowHelpPage_Test extends SWTBot_JUnit
{
	SWTBot_View aboutView;
	Browser     webBrowser;
	String      html;
	
	@Test
	public void openView()
	{
		assertTrue(true);
		aboutView = swtBot_View_Fast("TeamMentor");
		if (aboutView == null)
		{
			bot.menu("TeamMentor").menu("About TeamMentor Plugin").click();
			aboutView = swtBot_View_Fast("TeamMentor");
		}
		assertNotNull(aboutView);
		webBrowser = ((DefaultPart_WebBrowser)aboutView.viewPart).browser;
		assertNotNull(webBrowser);
		display.syncExec(new Runnable() { @Override public void run() 
		{
			html = webBrowser.getText();			
		}});
		//fail("this tests a fail");
	}
	@Test 
	public void checkHtmlContent()
	{
		openView();
		assertNotNull(aboutView);
		assertNotNull(webBrowser);
		assertNotNull(html);
		assertContains("TeamMentor", html);	
		assertContains("<html>", html);
		assertContains("</html>", html);
		
		assertFalse    ("HeaderImage.jpg ref should be local",html.contains("https://vulnerabilities.teammentor.net/Images/HeaderImage.jpg"));
	}
}
