package g2.java.api;

import g2.scripts.views.DefaultPart_WebBrowser;
import groovy.lang.Binding;

import org.codehaus.groovy.runtime.MethodClosure;
import org.eclipse.swt.browser.Browser;

public class TeamMentorAPI 
{
	public static EclipseAPI eclipseAPI;
	//public static String BrowserID_TeamMentor_Article = "TeamMentor Articles";
	public static String Server 	= "https://teammentor.net";
	public static String SessionId  = "9e78f231-106b-4f73-a10f-22ab9ebee435";
	public static Browser lastBrowser;
	
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
		Browser.setCookie("Session=" + SessionId, Server);//"https://teammentor.net");
		return SessionId;
	}	
	public static void setServer(String newServer)
	{
		Server = newServer;
	}
	public static void setSession(String session)
	{
		SessionId = session;
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
		new Thread()
		{
		    public void run() 
		    {
		    	System.out.println("blah");
		    	try 
		    	{
					Thread.sleep(500);
				}
		    	catch (InterruptedException e) 
				{ 
					e.printStackTrace();
				}
		    	org.eclipse.swt.widgets.Display.getDefault().asyncExec(new Runnable()
				{
					@Override
					public void run()
					{
						//browserView.browser.execute("alert(13) ");						
						browser.execute("$('.ItemDetailTable').show(); ");
					}
				});
		        
		    }
		}.start();
		
		//browserView.browser.execute("alert(13) ");	
		//eclipseAPI.open_Url_in_WebBrowser(articleId,"javascript:alert(12")
//		browserView.browser.execute("$('.ItemDetailTable').show(); ");
	}		
	
	public static Browser open_Article_Page(String mode, String articleId)
	{
		String tmUrl = Server + "/" + mode + "/" + articleId; 
		//eclipseAPI.open_Url_in_WebBrowser(BrowserID_TeamMentor_Article, tmUrl);
		lastBrowser = eclipseAPI.open_Url_in_WebBrowser(articleId, tmUrl).browser;
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
		setSession("9e78f231-106b-4f73-a10f-22ab9ebee435");
		open_Article("81a240be-b2a2-411a-b54e-0f2e86d74b40");
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
