package g2.java.api;

import g2.scripts.views.DefaultPart_WebBrowser;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;


@SuppressWarnings("restriction")
public class EclipseAPI 
{
	public static IWorkbench workbench;
	
	public EclipseAPI()
	{	
		workbench = PlatformUI.getWorkbench();
	}	
		
	public MenuManager getTopMenuManager()
	{
		/*workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow[] workbenchWindows = workbench.getWorkbenchWindows();		
		if (workbenchWindows.length > 0)
		{
			workbench.getActiveWorkbenchWindow().get
			IWorkbenchWindow workbenchWindow =  workbenchWindows[0];			
			MenuManager topMenuManager = workbenchWindow.getActionBars().getMenuManager();			
			return topMenuManager;
		}*/
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		return ((WorkbenchWindow)window).getMenuManager();			
	}		
	
	public MenuManager addMenu(String menuName)
	{
		MenuManager topMenuManager = getTopMenuManager();
		if (topMenuManager != null)
		{
			MenuManager newMenu = new MenuManager(menuName);
			topMenuManager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, newMenu);
			
			return newMenu;
		}
		return null;
	}
	
	public MenuManager add_MenuItem_ShowMessage(MenuManager targetMenu, String menuName, final String messageTitle, final String messageBody)
	{		
		Action sampleAction = new Action(menuName) {
			public void run()
			{
				MessageDialog.openInformation(null, messageTitle, messageBody);
			}};		
		targetMenu.add(sampleAction);	
		targetMenu.update(true);				
		return targetMenu;		
	}
	
	public EclipseAPI add_MenuItem_OpenWebPage(MenuManager menu, final String menuName, final String urlToOpen)
	{
		final EclipseAPI eclipseAPI = this;	
		Action sampleAction = new Action(menuName) {
			public void run()
			{
				eclipseAPI.open_Url_in_WebBrowser(menuName,urlToOpen);				
			}};
			
		
		menu.add(sampleAction);
		getTopMenuManager().update(true);		
		return this;
	}
	
	public EclipseAPI add_MenuItem_Article(MenuManager menu, final String menuName, final String articleId)
	{		
		Action sampleAction = new Action(menuName) {
			public void run()
			{
				TeamMentorMenu.open_Article(articleId);		
			}};			
		
		menu.add(sampleAction);
		getTopMenuManager().update(true);		
		return this;
	}
	
	public EclipseAPI add_MenuItem_LoginToTM(MenuManager menu)
	{		
		Action sampleAction = new Action("Login into TM") {
			public void run()
			{								 				
				String sessionId = TeamMentorMenu.loginIntoTM();
				MessageDialog.openInformation(null, "TeamMentor", "Logged in into TM using sessionId " + sessionId);
			}};			
		
		menu.add(sampleAction);
		getTopMenuManager().update(true);		
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

