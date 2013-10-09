/*package g2.groovy.api
import org.eclipse.jface.action.*
import org.eclipse.jface.dialogs.*
import org.eclipse.swt.widgets.Menu
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;
import org.eclipse.ui.IWorkbenchWindow;


@SuppressWarnings("restriction")
public class EclipseAPI 
{
	public static Workbench workbench;
	
	public EclipseAPI()
	{	
		workbench = PlatformUI.getWorkbench();
	}	
		
	public MenuManager GetTopMenuManager(boolean update)
	{
		workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow[] workbenchWindows = workbench.getWorkbenchWindows();
		int size = workbenchWindows.size(); 
		if (size > 0)
		{
			IWorkbenchWindow workbenchWindow =  workbenchWindows.first();			
			MenuManager topMenuManager = workbenchWindow.getActionBars().getMenuManager();
			if (update)
				topMenuManager.update(true);
			return topMenuManager;
		}
		return null;
	}		
	
	public EclipseAPI addMenu(String menuName, Closure withNewMenu)
	{
		def topMenuManager = GetTopMenuManager(false);
		if (topMenuManager != null)
		{
			def menu = new MenuManager(menuName);
			topMenuManager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
			
			withNewMenu(menu)
		}
		return this;	
	}
	
	public EclipseAPI add_MenuItem_ShowMessage(MenuManager menu, String menuName, String messageTitle, String messageBody)
	{
		Action sampleAction = new Action(menuName) {
			public void run()
			{
				MessageDialog.openInformation(null, messageTitle, messageBody);
			}};		
		menu.add(sampleAction);		
		GetTopMenuManager(true);			
		return this;		
	}
	
	public EclipseAPI add_MenuItem_OpenWebPage(MenuManager menu, String menuName, String urlToOpen)
	{
		Action sampleAction = new Action(menuName) {
			public void run()
			{
				this.add_WebBrowser(menuName,urlToOpen);				
			}};
		
		menu.add(sampleAction);
		GetTopMenuManager(true);		
		return this;
	}
	
	
	public IWebBrowser add_WebBrowser(String browserId, String urlToOpen)
	{	
		//int style = IWorkbenchBrowserSupport.AS_EDITOR | IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.STATUS;
		int style = IWorkbenchBrowserSupport.AS_VIEW | IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.STATUS;		
		def browser = WorkbenchBrowserSupport.getInstance().createBrowser(style, browserId, browserId, browserId);
		browser.openURL(new URL(urlToOpen));
		return browser;
	}
	

}
*/