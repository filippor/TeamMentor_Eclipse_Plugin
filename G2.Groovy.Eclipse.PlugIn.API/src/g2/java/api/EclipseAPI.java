package g2.java.api;

import g2.groovy.api.TestGroovy;
import g2.groovy.api.Tree_ExtensionMethods;
import g2.scripts.views.DefaultPart_WebBrowser;
import groovy.lang.Binding;

import org.codehaus.groovy.eclipse.preferences.DebuggerPreferencesPage;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;


@SuppressWarnings("restriction")
public class EclipseAPI 
{
	public IWorkbenchWindow activeWorkbenchWindow;
	public IWorkbench 		workbench;	
	public Display	 		display;
	public Shell   	    	shell;
	public TestGroovy   	testGroovy;	
	public IWorkspace   	workspace;
	
	static 
	{
		Tree_ExtensionMethods.setExtensionmethods();
	}
	
	public EclipseAPI()
	{	
				
		captureEclipseObjects();
	}	
	
	
	public EclipseAPI captureEclipseObjects()
	{		
		try
		{
			workbench 			  = PlatformUI.getWorkbench();
			display    			  = workbench.getDisplay();
			activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
			shell 	   			  = activeWorkbenchWindow.getShell();		
			workspace  			  = ResourcesPlugin.getWorkspace();
			testGroovy 			  = new TestGroovy();
		}
		catch(Exception ex)
		{
			
		}
		
		return this;
	}		
	
	//helpers
	
	public IWorkbench 	    workbench()
	{
		return workbench;
	}
	
	public IWorkbenchWindow activeWorkbenchWindow()
	{
		if (workbench != null)
			return workbench.getActiveWorkbenchWindow();
		return null;
	}
	
	public IWorkbenchPage 	activePage()
	{
		IWorkbenchWindow workbenchWindow = activeWorkbenchWindow();
		if (workbenchWindow != null)
		 	return workbenchWindow.getActivePage();
		return null;
	}
	
	
	public MenuManager getTopMenuManager()
	{		
		if (workbench != null)
		{
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			return ((WorkbenchWindow)window).getMenuManager();
		}
		return null;
	}		
	
	public MenuManager addMenu(String menuName)
	{
		MenuManager topMenuManager = getTopMenuManager();
		if (topMenuManager == null)
			return null;
							
		MenuManager newMenu = new MenuManager(menuName);
		topMenuManager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, newMenu);
		
		return newMenu;		
	}
	
	public MenuManager add_MenuItem_ShowMessage(MenuManager targetMenu, String menuName, final String messageTitle, final String messageBody)
	{		
		if (targetMenu == null)
			return null;
		
		Action action = new Action(menuName) {
			public void run()
			{
				MessageDialog.openInformation(null, messageTitle, messageBody);
			}};		
		targetMenu.add(action);	
		targetMenu.update(true);				
		return targetMenu;		
	}
	
	public EclipseAPI add_MenuItem_OpenWebPage(MenuManager menu, final String menuName, final String urlToOpen)
	{
		if (menu !=null)
		{				
			final EclipseAPI eclipseAPI = this;	
			Action action = new Action(menuName) {
				public void run()
				{
					eclipseAPI.open_Url_in_WebBrowser(menuName,urlToOpen);				
				}};
				
			
			menu.add(action);
			getTopMenuManager().update(true);
		}
		return this;
	}
	
	public EclipseAPI add_MenuItem_Article(MenuManager menu, final String menuName, final String articleId)
	{		
		if (menu!=null)
		{
			Action action = new Action(menuName) {
				public void run()
				{
					TeamMentorAPI.open_Article(articleId);		
				}};			
			
			menu.add(action);
			getTopMenuManager().update(true);
		}
		return this;
	}
	
	public EclipseAPI add_MenuItem_LoginToTM(MenuManager menu)
	{		
		if (menu!=null)
		{
			Action sampleAction = new Action("Login into TM") {
				public void run()
				{								 				
					String sessionId = TeamMentorAPI.loginIntoTM();
					MessageDialog.openInformation(null, "TeamMentor", "Logged in into TM using sessionId " + sessionId);
				}};			
			
			menu.add(sampleAction);
			getTopMenuManager().update(true);
		}
		return this;
	}
		
	
	public DefaultPart_WebBrowser open_Url_in_WebBrowser(String browserId, String urlToOpen)
	{	
		try 
		{
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			DefaultPart_WebBrowser webBrowserPart = (DefaultPart_WebBrowser)page.showView(DefaultPart_WebBrowser.ID, browserId, IWorkbenchPage.VIEW_ACTIVATE);
			webBrowserPart.browser.setUrl(urlToOpen);
			webBrowserPart.setName(urlToOpen);
			return webBrowserPart;			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return null;
	}
	

}

