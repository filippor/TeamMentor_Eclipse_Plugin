package g2.java.api;

import org.eclipse.jface.action.*;
import org.eclipse.swt.browser.Browser;


public class TeamMentorMenu 
{
	public static MenuManager TM_Menu;	
	public static EclipseAPI eclipseAPI;
	
	public static String BrowserID_TeamMentor_Article = "TeamMentor Articles";
	
	public static void createTeamMentorMenu()
	{
		eclipseAPI = new EclipseAPI();
		
		//public void withNewMenu(MenuManager menu)
		//{
		//	eclipseAPI.Add_MenuItem_ShowMessahe(menu,'4rd menu','title','message')
		//}
		
		//eclipseAPI.AddMenu ('TeamMentor', {menu->withNewMenu(menu)});
		
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
	
	public static void open_Article(String articleId)
	{
		String tmUrl = "http://teammentor.net/article/" + articleId; 
		eclipseAPI.open_Url_in_WebBrowser(BrowserID_TeamMentor_Article, tmUrl);		
	}
	public static String loginIntoTM()
	{
		String sessionId = "9e78f231-106b-4f73-a10f-22ab9ebee435";
		Browser.setCookie("Session=" + sessionId,"https://teammentor.net");
		return sessionId;
	}
	
	/*public static void add_MenuItem_Article_InSession(String articleId)
	{
		String tmUrl = "http://teammentor.net/article/" + articleId; 
		
		webBrowserPart.browser.setUrl(tmUrl);
				
	}*/
	
	//browser;
}
