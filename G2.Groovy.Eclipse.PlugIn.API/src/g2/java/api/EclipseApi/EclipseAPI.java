package g2.java.api.EclipseApi;

import g2.groovy.api.TestGroovy;
import g2.groovy.api.Tree_ExtensionMethods;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class EclipseAPI 
{
	public IWorkbenchWindow activeWorkbenchWindow;
	public IWorkbench 		workbench;	
	public Display	 		display;
	public Shell   	    	shell;
	public TestGroovy   	testGroovy;	
	public IWorkspace   	workspace;
	
	public Menus			menus;
	public Panels			panels;
	
	static 
	{
		Tree_ExtensionMethods.setExtensionmethods();
	}
	
	public EclipseAPI()
	{	
		captureEclipseObjects();
		menus  = new Menus(workbench);
		panels = new Panels(workbench);
		
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
		catch(Exception e)
		{
			e.printStackTrace();	
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
		{
			return workbench.getActiveWorkbenchWindow();
		}
		return null;
	}
	
	public IWorkbenchPage 	activePage()
	{
		IWorkbenchWindow workbenchWindow = activeWorkbenchWindow();
		if (workbenchWindow != null)
		 	return workbenchWindow.getActivePage();
		return null;
	}
		
}

