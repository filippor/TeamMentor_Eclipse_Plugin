package g2.scripts.actions;
import g2.java.api.TeamMentorAPI;
import g2.java.api.EclipseApi.Panels;
import g2.scripts.Activator;
import g2.scripts.views.DefaultPart_WebBrowser;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import teammentor.eclipse.plugin.fortify.preferences.PluginPreferences;

public class EnableTeamMentor implements IWorkbenchWindowActionDelegate 
{
	private IWorkbenchWindow window;	
	
	public EnableTeamMentor() 
	{
	}


	public void run(IAction action) 
	{
						
		String message = 	"<h4>TeamMentor Eclipse Plugin</h1>Welcome to Teammentor Eclipse Plugin for Fortify. You should now see TeamMentor Articles when you click on a Fortify Finding";
		
		TeamMentorAPI.show_Html_With_TeamMentor_Banner(message);
		
		
		
		
/*		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		DefaultPart_WebBrowser webBrowserPart;
		try 
		{
			webBrowserPart = (DefaultPart_WebBrowser)page.showView(DefaultPart_WebBrowser.ID, browserId, IWorkbenchPage.VIEW_ACTIVATE);
			webBrowserPart.browser.setText(htmlToShow);
		} catch (PartInitException e) 
		{
			e.printStackTrace();
		}*/			
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}