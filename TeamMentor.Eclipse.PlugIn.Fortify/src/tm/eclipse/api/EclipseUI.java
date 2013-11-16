package tm.eclipse.api;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public class EclipseUI 
{	
	public IWorkbench 		workbench;
	
	public EclipseUI(IWorkbench _workbench)
	{
		workbench = _workbench;
	}
	
	public IWorkbenchPage   activePage()
	{
		return (activeWorkbenchWindow() !=null)
					? activeWorkbenchWindow().getActivePage()
					: null;

	}
	 
	public IWorkbenchWindow activeWorkbenchWindow()
	{
		return (workbench!=null) 
					? workbench.getActiveWorkbenchWindow()
					: null;
				
		
	}
}
