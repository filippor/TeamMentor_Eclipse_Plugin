package tm.eclipse.api;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import groovy.lang.Binding;

import org.codehaus.groovy.runtime.MethodClosure;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swtbot.swt.finder.results.Result;

import tm.eclipse.ui.Activator;
import tm.eclipse.ui.PluginPreferences.MainPreferences;
import tm.eclipse.ui.PluginResources;
import tm.eclipse.ui.views.DefaultPart_WebBrowser;

public class TeamMentorAPI 
{
	public static EclipseAPI eclipseAPI;	
	public static Browser 	 lastBrowser;
	
	public TeamMentorAPI()
	{}
	
	
	public static void open_Article(String articleId)
	{
		loginIntoTM();
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
		Browser.setCookie("Session=" + MainPreferences.getSessionId(), MainPreferences.getServer());
		String session = Browser.getCookie("Session", MainPreferences.getServer());
		String server = MainPreferences.getServer();
		return MainPreferences.getSessionId();
	}	
	public static void setServer(String newServer)
	{
		MainPreferences.setServer(newServer);
	}
	public static void setSession(String session)
	{
		MainPreferences.setSessionId(session);
		loginIntoTM();
	}
	public static String getSession()
	{
		return MainPreferences.getSessionId();		
	}
	public static void edit_Notepad(String articleId)
	{
		open_Article_Page("notepad", articleId);
	}
	
	public static void edit_Wysiwyg(String articleId)
	{
		open_Article_Page("edit", articleId);
	}
		
/*	public static void edit_Wysiwyg_withMetadata(String articleId)
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
*/	
	public static Browser open_Article_Page(String mode, String articleId)
	{
		String tmUrl = MainPreferences.getServer() + "/" + mode + "/" + articleId; 		
		String browserId = (MainPreferences.openArticleInNewWindow()) ? articleId : MainPreferences.getDefaultBrowserId();
		lastBrowser = eclipseAPI.panelFactory.open_Url_in_WebBrowser(browserId, tmUrl).browser;
		return lastBrowser;
	}

	public static DefaultPart_WebBrowser show_Html_With_TeamMentor_Banner(final String htmlSnippet)
	{		
		return syncExec(new Result<DefaultPart_WebBrowser>() { public DefaultPart_WebBrowser run() 
			{
				PluginResources pluginResources = new PluginResources();				
				
				String headerImage = pluginResources.get_Resource_Saved_on_TempFolder("/images/jpgs/HeaderImage.jpg");
				String bootstrapCss = pluginResources.get_Resource_Saved_on_TempFolder("/images/css/bootstrap.css");
				String htmlToShow = "<html><header><link href='" + bootstrapCss + "' rel='stylesheet'></header>" +
									"<body><img src='" + headerImage + "' class='HeaderImage'/><br/><br/>" + 									 
									htmlSnippet +									 									
									"</body></html>";
				Panels panelFactory = new Panels(eclipseAPI.workbench); 
				return panelFactory.open_Html_in_WebBrowser(htmlToShow);
			}});
		
	}
	public static DefaultPart_WebBrowser show_No_ArticleMessage()
	{
		String htmlSnippet = "<h3>No article for selected finding</h3>" +
					  "Sorry, there is no article for the current Fortify mapping. Please send your views and requirements to" +
					  " <a href='mailto:suport@securityinnovation.com'>TeamMentor Support</a>";

		return show_Html_With_TeamMentor_Banner(htmlSnippet);
	}
	
	public static void setServer_CurrentSetup()
	{
		setServer(MainPreferences.getServer());
		setSession(MainPreferences.getSessionId()); 
	}
	public static String loginIntoTeamMentor(String username, String password) 
	{
		StringBuffer response = new StringBuffer();
		URL obj;
		try 
		{
			String url = String.format("%s/rest/login/%s/%s", MainPreferences.getServer() ,username, password);
			obj = new URL(url);
		
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			int responseCode = con.getResponseCode();		
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;			
	 
			while ((inputLine = bufferReader.readLine()) != null) 
			{
				response.append(inputLine);
			}
			bufferReader.close();
		} 
		catch (MalformedURLException e)  { e.printStackTrace(); }
		catch (IOException e) 		     { e.printStackTrace(); }
		String guid = response.toString().replace("<guid xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">","")
									     .replace("</guid>","");
		return guid;
	}
	
	public static void mapGroovyBindings(Binding binding) 
	{
		binding.setVariable("openArticle", new MethodClosure(TeamMentorAPI.class, "open_Article"));
		binding.setVariable("loginIntoTM", new MethodClosure(TeamMentorAPI.class, "loginIntoTM"));
		binding.setVariable("setServer"  , new MethodClosure(TeamMentorAPI.class, "setServer"));
		binding.setVariable("setSession" , new MethodClosure(TeamMentorAPI.class, "setSession"));
		binding.setVariable("getSession" , new MethodClosure(TeamMentorAPI.class, "getSession"));
		
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
		
		binding.setVariable("browser"      , lastBrowser);		
		
		binding.setVariable("inspect"     , new MethodClosure( groovy.inspect.swingui.ObjectBrowser.class	   , "inspect"));
		binding.setVariable("show"        , new MethodClosure( groovy.inspect.swingui.ObjectBrowser.class	   , "inspect"));		
	}
}
