	package tm.eclipse.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.groovy.plugins.Plugin_Fortify;
import tm.eclipse.ui.PluginPreferences.MainPreferences;

public class Startup implements IStartup {

	public static EclipseAPI   eclipseApi;
	public static boolean      showDebugViews = true;
	public static List<Object> loadedPlugins = new ArrayList<Object>();
	//public static FortifyAPI fortifyApi;

	@Override
	public void earlyStartup() 
	{
		// this will be called on Eclipse startup on a separate thread
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() { @Override public void run() 
			{
				eclipseApi = new EclipseAPI();
				TeamMentorAPI.eclipseAPI = eclipseApi;
				TeamMentorAPI.setServer_CurrentSetup();				
				
			}});	
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() { @Override public void run() 
			{
				startDefaultTeamMentorPlugins();
			}});
	}
	
	public void startDefaultTeamMentorPlugins()
	{
		if (MainPreferences.loadPluginsOnStartup())
		{
			Object fortifyApi = new Plugin_Fortify().startup();
			if (fortifyApi ==null)
				eclipseApi.log("ERROR: Plugin_Fortify().startup() returned null");
			else
				loadedPlugins.add(fortifyApi);
			
		}
	}
}
