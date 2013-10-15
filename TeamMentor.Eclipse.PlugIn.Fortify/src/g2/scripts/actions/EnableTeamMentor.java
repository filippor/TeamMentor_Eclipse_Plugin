package g2.scripts.actions;
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

public class EnableTeamMentor implements IWorkbenchWindowActionDelegate 
{
	private IWorkbenchWindow window;	
	
	public EnableTeamMentor() 
	{
	}


	public void run(IAction action) 
	{
						
		IWorkbench workbench = Activator.eclipseApi.workbench;
		//final Panels panels = new Panels(workbench);	
				
		String browserId = "teammentor.enabled";
		IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
		DefaultPart_WebBrowser webBrowserPart;
		try 
		{
			webBrowserPart = (DefaultPart_WebBrowser)page.showView(DefaultPart_WebBrowser.ID, browserId, IWorkbenchPage.VIEW_ACTIVATE);
			webBrowserPart.browser.setText("<html>" + 
				    "<link href='http://getbootstrap.com/dist/css/bootstrap.css' rel='stylesheet'>" + 
					"<h1 class='alert alert-success'>TeamMentor Message</h1>Teammentor Fortify mappings are now enabled</html>");
		} catch (PartInitException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//DefaultPart_WebBrowser browser = panels.open_Url_in_WebBrowser("teammentor enabled","about:blank");
		
				
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}