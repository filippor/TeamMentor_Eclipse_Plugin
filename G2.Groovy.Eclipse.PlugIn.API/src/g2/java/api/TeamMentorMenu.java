package g2.java.api;

import org.eclipse.jface.action.*;


public class TeamMentorMenu 
{
	public static MenuManager TM_Menu;	
	public static EclipseAPI eclipseAPI;		
	
	public static void createTeamMentorMenu()
	{
		eclipseAPI = new EclipseAPI();
		TeamMentorAPI.eclipseAPI = eclipseAPI;

		TM_Menu = eclipseAPI.addMenu ("TeamMentor API (v1.0)");
		
		addDefaultMenuItems();			
	}
	
	public static void addDefaultMenuItems()
	{
		eclipseAPI.add_MenuItem_OpenWebPage(TM_Menu,"Open Google"		 ,"http://google.net");
		eclipseAPI.add_MenuItem_OpenWebPage(TM_Menu,"Open TeamMentor.Net","http://teammentor.net");
		eclipseAPI.add_MenuItem_Article    (TM_Menu,"XSS article"        ,"Cross-site Scripting");
		eclipseAPI.add_MenuItem_LoginToTM  (TM_Menu);
		eclipseAPI.add_MenuItem_ShowMessage(TM_Menu,"Say Hi"			 ,"TeamMentor Message","Hi");		
	} 	
	
	
	
	/*public static void add_MenuItem_Article_InSession(String articleId)
	{
		String tmUrl = "http://teammentor.net/article/" + articleId; 
		
		webBrowserPart.browser.setUrl(tmUrl);
				
	}*/
	
	//browser;
}
