package tm.eclipse.api;

import static org.junit.Assert.*;

import org.eclipse.swt.browser.Browser;
import org.junit.Test;

import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.ui.views.DefaultPart_WebBrowser;

public class TeamMentorAPI_Test 
{	
	TeamMentorAPI 		   teamMentorApi;
	EclipseAPI    		   elipseAPI;	
	DefaultPart_WebBrowser defaultWebBrowser;
	Browser				   webBrowser;
	String 				   html;
	
	public TeamMentorAPI_Test()
	{
		teamMentorApi = new TeamMentorAPI();
		elipseAPI     = teamMentorApi.eclipseAPI;
	}
	
	@Test
	public void TeamMentorAPI_Ctor()
	{
		assertNotNull(teamMentorApi);
		assertNotNull(TeamMentorAPI.eclipseAPI);		
	}	
	
	@Test 
	public void show_Html_With_TeamMentor_Banner()
	{		
		final String htmlToShow = "...html to show ... "; 
		
		elipseAPI.invokeOnThread(new Runnable() { @Override public void run() 
			{
				defaultWebBrowser = TeamMentorAPI.show_Html_With_TeamMentor_Banner(htmlToShow);
				assertNotNull(defaultWebBrowser);
				assertNotNull(defaultWebBrowser.browser);
				webBrowser = defaultWebBrowser.browser;
			}});
		
		elipseAPI.invokeOnThread(new Runnable() { @Override public void run()
			{
				html = defaultWebBrowser.browser.getText();
				assertNotEquals("", html);
				assertTrue(html.contains(htmlToShow));
			}});
		
	}
	
}

