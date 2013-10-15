package g2.scripts.actions;
import g2.java.api.EclipseApi.Panels;
import g2.scripts.Activator;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class OpenTeamMentor implements IWorkbenchWindowActionDelegate 
{
	private IWorkbenchWindow window;	
	
	public OpenTeamMentor() 
	{
	}


	public void run(IAction action) 
	{
						
		IWorkbench workbench = Activator.eclipseApi.workbench;
		final Panels panels = new Panels(workbench);	
		
		panels.open_Url_in_WebBrowser("teammentor.net","http://teammentor.net");											
				
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}