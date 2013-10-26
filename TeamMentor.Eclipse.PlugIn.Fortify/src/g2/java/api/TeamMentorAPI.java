package g2.java.api;

import g2.java.api.EclipseApi.EclipseAPI;
import g2.java.api.EclipseApi.Panels;
import g2.scripts.views.DefaultPart_WebBrowser;
import groovy.lang.Binding;
import org.codehaus.groovy.runtime.MethodClosure;
import org.eclipse.swt.browser.Browser;
import teammentor.eclipse.plugin.fortify.preferences.PluginPreferences;

public class TeamMentorAPI 
{
	public static EclipseAPI eclipseAPI;	
	public static Browser 	 lastBrowser;
	
	public TeamMentorAPI()
	{}
	
	
	public static void open_Article(String articleId)
	{
		open_Article_Page("article", articleId);
	}
	
	public static void view_Article(String articleId)
	{
		open_Article_Page("article", articleId);
	}
	public static void view_Raw(String articleId)
	{
		open_Article_Page("raw", articleId);
	}
	public static void view_Html(String articleId)
	{
		open_Article_Page("html", articleId);
	}
	public static void view_Content(String articleId)
	{
		open_Article_Page("content", articleId);
	}
	public static void view_Jsonp(String articleId)
	{
		open_Article_Page("jsonp", articleId);
	}
	public static String loginIntoTM()
	{		
		Browser.setCookie("Session=" + PluginPreferences.getSessionId(), PluginPreferences.getServer());
		return PluginPreferences.getSessionId();
	}	
	public static void setServer(String newServer)
	{
		PluginPreferences.setServer(newServer);
	}
	public static void setSession(String session)
	{
		PluginPreferences.setSessionId(session);
		loginIntoTM();
	}
	public static void edit_Notepad(String articleId)
	{
		open_Article_Page("notepad", articleId);
	}
	
	public static void edit_Wysiwyg(String articleId)
	{
		open_Article_Page("edit", articleId);
	}
		
	public static void edit_Wysiwyg_withMetadata(String articleId)
	{
		final Browser  browser =  open_Article_Page("edit", articleId);
		new Thread() {
		    public void run() {		    			    	
				try 
				{
					Thread.sleep(500);
					org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable()
					{
						@Override
						public void run()
						{					
							browser.execute("$('.ItemDetailTable').show(); ");
						}
					});
				}
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}	        
		    }
		}.start();
				
	}		
	
	public static Browser open_Article_Page(String mode, String articleId)
	{
		String tmUrl = PluginPreferences.getServer() + "/" + mode + "/" + articleId; 		
		String browserId = (PluginPreferences.openArticleInNewWindow()) ? articleId : PluginPreferences.getDefaultBrowserId();
		lastBrowser = eclipseAPI.panels.open_Url_in_WebBrowser(browserId, tmUrl).browser;
		return lastBrowser;
	}

	public static DefaultPart_WebBrowser show_Html_With_TeamMentor_Banner(String htmlSnippet)
	{
		String htmlToShow = "<html><header><link href='http://getbootstrap.com/dist/css/bootstrap.css' rel='stylesheet'></header>" +
							"<body><img src='" + PluginPreferences.getServer() + "/Images/HeaderImage.jpg' class='HeaderImage'/><br/><br/>" + 
							 
							htmlSnippet +
							"" + 
							"</body></html>";
		Panels panels = new Panels(eclipseAPI.workbench); 
		return panels.open_Html_in_WebBrowser(htmlToShow);
		
	}
	public static DefaultPart_WebBrowser show_No_ArticleMessage()
	{
		String htmlSnippet = "<h3>No article for selected finding</h3>" +
					  "Sorry, there is no article for the current Fortify mapping. Please send your views and requirements to" +
					  " <a href='mailto:suport@securityinnovation.com'>TeamMentor Support</a>";

		return show_Html_With_TeamMentor_Banner(htmlSnippet);
	}
	
	//hardcoded servers and sessions
	
	/*public static void setServer_Local()
	{	
		setServer("http://localhost:12120");
		setSession("00000000-0000-0000-0000-000000000000");
		open_Article("81a240be-b2a2-411a-b54e-0f2e86d74b40");
	}*/
	public static void setServer_CurrentSetup()
	{
		setServer(PluginPreferences.getServer());
		setSession(PluginPreferences.getSessionId()); 
	}
	
	/*public static void setServer_TeamMentor()
	{	
		setServer("https://teammentor.net");
		setSession("00000000-0000-0000-0000-000000000000"); // logged in as fortify-plugin account
		//open_Article("81a240be-b2a2-411a-b54e-0f2e86d74b40");
	}
	public static 	void setServer_OWASP()
	{	
		setServer("http://owasp.teammentor.net");
		setSession("00000000-0000-0000-0000-000000000000");
		open_Article("57b928e2-5bc1-4d98-b3df-c7cca05dc5a8");
	}*/
	
	
	public static void mapGroovyBindings(Binding binding) 
	{
		binding.setVariable("openArticle", new MethodClosure(TeamMentorAPI.class, "open_Article"));
		binding.setVariable("loginIntoTM", new MethodClosure(TeamMentorAPI.class, "loginIntoTM"));
		binding.setVariable("setServer"  , new MethodClosure(TeamMentorAPI.class, "setServer"));
		binding.setVariable("setSession" , new MethodClosure(TeamMentorAPI.class, "setSession"));
		
		binding.setVariable("open"		 , new MethodClosure(TeamMentorAPI.class, "view_Article"));		
		binding.setVariable("viewArticle", new MethodClosure(TeamMentorAPI.class, "view_Article"));		
		binding.setVariable("viewRaw"    , new MethodClosure(TeamMentorAPI.class, "view_Raw"));
		binding.setVariable("viewHtml"   , new MethodClosure(TeamMentorAPI.class, "view_Html"));
		binding.setVariable("viewContent", new MethodClosure(TeamMentorAPI.class, "view_Content"));
		binding.setVariable("viewJsonp"  , new MethodClosure(TeamMentorAPI.class, "view_Jsonp"));
		
		binding.setVariable("editArticle"  , new MethodClosure(TeamMentorAPI.class, "edit_Wysiwyg"));
		binding.setVariable("editMetadata" , new MethodClosure(TeamMentorAPI.class, "edit_Wysiwyg_withMetadata"));
		binding.setVariable("openEdit"     , new MethodClosure(TeamMentorAPI.class, "edit_Wysiwyg"));		
		binding.setVariable("openNotepad"  , new MethodClosure(TeamMentorAPI.class, "edit_Notepad"));
		
		binding.setVariable("openPage"     , new MethodClosure(TeamMentorAPI.class, "open_Article_Page"));
		
//		binding.setVariable("setServer_Local"     , new MethodClosure(TeamMentorAPI.class, "setServer_Local"));		
//		binding.setVariable("setServer_TeamMentor", new MethodClosure(TeamMentorAPI.class, "setServer_TeamMentor"));
//		binding.setVariable("setServer_OWASP"     , new MethodClosure(TeamMentorAPI.class, "setServer_OWASP"));
		
		binding.setVariable("browser"     , lastBrowser);
		binding.setVariable("eclipseAPI"  , TeamMentorAPI.eclipseAPI);
		binding.setVariable("teammentorAPI", TeamMentorAPI.class);
		
		binding.setVariable("inspect"     , new MethodClosure( groovy.inspect.swingui.ObjectBrowser.class, "inspect"));
		binding.setVariable("show"        , new MethodClosure( groovy.inspect.swingui.ObjectBrowser.class, "inspect"));
	}
}
