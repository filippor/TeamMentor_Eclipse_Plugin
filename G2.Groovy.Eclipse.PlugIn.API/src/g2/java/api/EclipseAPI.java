package g2.java.api;

import g2.scripts.views.WebBrowser;

import java.net.URL;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;


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
	
	
	public WebBrowser open_Url_in_WebBrowser(String browserId, String urlToOpen)
	{	
		try {
			//int style = IWorkbenchBrowserSupport.AS_EDITOR | IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.STATUS;
			/*int style = IWorkbenchBrowserSupport.AS_VIEW | IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.STATUS;		
			IWebBrowser browser;
			
			browser = WorkbenchBrowserSupport.getInstance().createBrowser(style, browserId, browserId, browserId);
			
			browser.openURL(new URL(urlToOpen));
			return browser;*/
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			WebBrowser webBrowser = (WebBrowser)page.showView(WebBrowser.ID, browserId, IWorkbenchPage.VIEW_ACTIVATE);
			webBrowser.webBrowser.setUrl(urlToOpen);
			return webBrowser;
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return null;
	}
	

}

