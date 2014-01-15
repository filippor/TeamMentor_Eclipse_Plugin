package tm.eclipse.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;










import static tm.eclipse.helpers.log.*;
import tm.eclipse.helpers.Images;
import tm.eclipse.swt.Mouse;
import tm.eclipse.swt.controls.extra.ObjectBrowser;
import tm.eclipse.swt.controls.extra.ObjectViewer;
//import tm.eclipse.groovy.TestGroovy;
//import tm.eclipse.groovy.Tree_ExtensionMethods;
import tm.eclipse.ui.EclipsePartEvents;

public class EclipseAPI 
{
	public static EclipsePartEvents partEvents;
	public HashMap<String,Object> 	objects;
	public List<String>     	  	extraGroovyJars;
	
	public IWorkbenchWindow activeWorkbenchWindow;	
	public IWorkbenchPage	activeWorkbenchPage;
	public IWorkbench 		workbench;	
	public Display	 		display;
	public Shell   	    	shell;
//	public TestGroovy   	testGroovy;	
	public IWorkspace   	workspace;
	
	public Images			images;
	public Plugin			plugin;
	public Console			console;
	public Menus			menus;
	public Panels		    panelFactory;
	public Views			views;
	public Editors		    editors;
	public Mouse			mouse;
	public Platform			platform;
	public Registry		    registry;
	public Utils		    utils;
	public boolean			ready;
	
	/*static 
	{
		Tree_ExtensionMethods.setExtensionmethods();		
	}*/
	
	public EclipseAPI() 
	{	
		display = PlatformUI.getWorkbench().getDisplay();
		display.syncExec(new Runnable() { public void run() 
			{
				objects 		= new HashMap<String,Object>();
				extraGroovyJars = new ArrayList<String>();
				 
				captureEclipseObjects();
				images		 = new Images();
				mouse 		 = new Mouse(display);
				menus  		 = new Menus(workbench);
				panelFactory = new Panels(workbench);
				views  		 = new Views(EclipseAPI.this);
				editors  	 = new Editors(EclipseAPI.this);
				plugin 	 	 = new Plugin(EclipseAPI.this);
				console 	 = new Console();
				platform 	 = new Platform(EclipseAPI.this);
				registry 	 = new Registry(EclipseAPI.this);
				utils 	     = new Utils(EclipseAPI.this);
				
				
				
				setEclipsePartEvents();
				ready = true;
			}});
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
//			display    			  = workbench.getDisplay();				
			activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
			activeWorkbenchPage	  = activeWorkbenchWindow.getActivePage();
			shell 	   			  = activeWorkbenchWindow.getShell();		
			workspace  			  = ResourcesPlugin.getWorkspace();
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
/*	public IWorkbenchWindow activeWorkbenchWindow()
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
	}*/
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
		info(message);
		return this;
	}	
	public String ping()
	{
		return "Pong ...";
	}
	
	public EclipseAPI syncExec(final Runnable runnable)
	{
		UIThreadRunnable.syncExec(display, new VoidResult() { public void run()	
			{
				runnable.run();
			}});
		return this;
	}
	
	public ObjectBrowser show(Object object)
	{
		return ObjectBrowser.show_ObjectBrowser(object);
	}
}

