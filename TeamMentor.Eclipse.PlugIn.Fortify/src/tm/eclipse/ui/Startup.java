package tm.eclipse.ui;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.groovy.plugins.Plugin_Fortify;

public class Startup implements IStartup {

	public static EclipseAPI eclipseApi;
	public static boolean    showDebugViews = true;
	//public static FortifyAPI fortifyApi;

	@Override
	public void earlyStartup() 
	{
		// this will be called on Eclipse startup on a separate thread
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() { @Override public void run() 
			{
				eclipseApi = new EclipseAPI();
				TeamMentorAPI.eclipseAPI = eclipseApi;
				TeamMentorAPI.setServer_CurrentSetup();
				
				startDefaultTeamMentorPlugins();
			}});		
	}
	
	public void startDefaultTeamMentorPlugins()
	{
		new Plugin_Fortify().startup();		
	}
}
