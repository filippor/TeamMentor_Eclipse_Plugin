package g2.java.api.EclipseApi;

import g2.java.api.TeamMentorAPI;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.WorkbenchWindow;

//@SuppressWarnings("restriction")
public class Menus 
{
	public IWorkbench 		workbench;
	
	public Menus(IWorkbench _workbench)
	{
		workbench = _workbench;
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
	
	public MenuManager add_Menu(String menuName)
	{
		MenuManager topMenuManager = getTopMenuManager();
		if (topMenuManager == null)
		{
			return null;
		}
							
		MenuManager newMenu = new MenuManager(menuName);
		topMenuManager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, newMenu);
		
		return newMenu;			
	}
	
	public MenuManager add_MenuItem_ShowMessage(MenuManager targetMenu, String menuName, final String messageTitle, final String messageBody)
	{		
		if (targetMenu == null)
		{
			return null;
		}
		
		Action action = new Action(menuName) {
			public void run()
			{
				MessageDialog.openInformation(null, messageTitle, messageBody);
			}};		
		targetMenu.add(action);	
		targetMenu.update(true);				
		return targetMenu;		
	}
	
	public Menus add_MenuItem_OpenWebPage(MenuManager menu, final String menuName, final String urlToOpen)
	{
		if (menu !=null)
		{				
			final Panels panels = new Panels(workbench);	
			Action action = new Action(menuName) {
				public void run()
				{
					panels.open_Url_in_WebBrowser(menuName,urlToOpen);				
				}};
				
			
			menu.add(action);
			getTopMenuManager().update(true);
		}
		return this;
	}
	
	public Menus add_MenuItem_Article(MenuManager menu, final String menuName, final String articleId)
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
	
	public Menus add_MenuItem_LoginToTM(MenuManager menu)
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
}
