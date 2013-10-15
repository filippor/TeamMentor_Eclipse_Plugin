package g2.java.api;

import g2.java.api.EclipseApi.EclipseAPI;
import groovy.lang.Binding;

import org.codehaus.groovy.runtime.MethodClosure;
import org.eclipse.swt.browser.Browser;

public class TeamMentorAPI 
{
	public static EclipseAPI eclipseAPI;
	public static String 	 server 	= "https://teammentor.net";
	public static String 	 sessionId  = "3d4732ca-dfe3-4db0-943e-02ad4e0c6f29";
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
		Browser.setCookie("Session=" + sessionId, server);
		return sessionId;
	}	
	public static void setServer(String newServer)
	{
		server = newServer;
	}
	public static void setSession(String session)
	{
		sessionId = session;
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
		String tmUrl = server + "/" + mode + "/" + articleId; 
		lastBrowser = eclipseAPI.panels.open_Url_in_WebBrowser(articleId, tmUrl).browser;
		return lastBrowser;
	}

	//hardcoded servers and sessions
	
	public static void setServer_Local()
	{	
		setServer("http://localhost:12120");
		setSession("b6cb4978-d134-4794-a435-72596bc796a2");
		open_Article("81a240be-b2a2-411a-b54e-0f2e86d74b40");
	}
	
	public static void setServer_TeamMentor()
	{	
		setServer("http://www.teammentor.net");
		setSession("5d065953-6603-4eca-a1e8-9d64a70a399d"); // logged in as fortify-plugin account
		//setServer("http://checkmarx.teammentor.net"); 
		//setSession("6cd0322a-191d-4eea-b26f-47ef6a1ab2d9");
		//open_Article("81a240be-b2a2-411a-b54e-0f2e86d74b40");
	}
	public static 	void setServer_OWASP()
	{	
		setServer("http://owasp.teammentor.net");
		setSession("00000000-0000-0000-0000-000000000000");
		open_Article("57b928e2-5bc1-4d98-b3df-c7cca05dc5a8");
	}
	
	
	
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
		
		binding.setVariable("setServer_Local"     , new MethodClosure(TeamMentorAPI.class, "setServer_Local"));		
		binding.setVariable("setServer_TeamMentor", new MethodClosure(TeamMentorAPI.class, "setServer_TeamMentor"));
		binding.setVariable("setServer_OWASP"     , new MethodClosure(TeamMentorAPI.class, "setServer_OWASP"));
		
		binding.setVariable("browser"     , lastBrowser);
		binding.setVariable("eclipseAPI"     , TeamMentorAPI.eclipseAPI);
		
		binding.setVariable("inspect"     , new MethodClosure( groovy.inspect.swingui.ObjectBrowser.class, "inspect"));
		binding.setVariable("show"        , new MethodClosure( groovy.inspect.swingui.ObjectBrowser.class, "inspect"));
		
		
	}
}
