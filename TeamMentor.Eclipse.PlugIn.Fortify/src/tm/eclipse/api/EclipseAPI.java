package tm.eclipse.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import static tm.eclipse.api.EclipseLog.*;



//import tm.eclipse.groovy.TestGroovy;
//import tm.eclipse.groovy.Tree_ExtensionMethods;
import tm.eclipse.ui.EclipsePartEvents;

public class EclipseAPI 
{
	public static EclipsePartEvents partEvents;
	public HashMap<String,Object> 	objects;
	public List<String>     	  	extraGroovyJars;
	
	public IWorkbenchWindow activeWorkbenchWindow;	
	public IWorkbench 		workbench;	
	public Display	 		display;
	public Shell   	    	shell;
//	public TestGroovy   	testGroovy;	
	public IWorkspace   	workspace;
	
	public Menus			menus;
	public Panels			panels;
	public Views			views;
	
	/*static 
	{
		Tree_ExtensionMethods.setExtensionmethods();		
	}*/
	
	public EclipseAPI()
	{	
		objects 		= new HashMap<String,Object>();
		extraGroovyJars = new ArrayList<String>();
		
		captureEclipseObjects();
		menus  = new Menus(workbench);
		panels = new Panels(workbench);		
		views  = new Views(workbench);		
		setEclipsePartEvents();
	}	
	
	public EclipseAPI setEclipsePartEvents()
	{
		if(partEvents==null)
		{ 
			partEvents = new EclipsePartEvents();

			IPartService partService = workbench.getActiveWorkbenchWindow().getPartService();
			partService.addPartListener(partEvents);
		}
		return this;
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
//			testGroovy 			  = new TestGroovy();
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
	public EclipseAPI       alert(String message)
	{		
		MessageDialog.openInformation(shell,"Message",message);
		return this;
	}
	public EclipseAPI       log(String message)
	{		
		/*StatusManager manager = StatusManager.getManager();
		manager.handle(new Status(Status.INFO, "Message", message));		
		*/
		log_Info(message);
		return this;
	}
	public EclipseAPI       invokeOnThread(Runnable runnable)
	{
		display.syncExec(runnable);
		return this;
	}
	public String ping()
	{
		return "Pong ...";
	}
		
}

