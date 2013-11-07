package tm.eclipse.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.groovy.FortifyAPI;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin 
{
	public static final String PLUGIN_ID = "TeamMentor.Eclipse.Plugin.Fortify"; 

	public static Activator plugin;
	
	public static EclipseAPI eclipseApi;
	public static FortifyAPI fortifyApi;

	
	/**
	 * This is the first plugin method to be invoked
	 */
	public Activator() 
	{
		
	}

	public void start(BundleContext context)  throws Exception
	{		
		super.start(context);
		plugin = this;
		
		//TeamMentorMenu.createTeamMentorMenu();
		eclipseApi = new EclipseAPI();
		fortifyApi = new FortifyAPI(eclipseApi);
		TeamMentorAPI.eclipseAPI = eclipseApi;
		TeamMentorAPI.setServer_CurrentSetup();		
	}
	
	public void stop(BundleContext context)  throws Exception
	{				
		plugin = null;
		super.stop(context);
	}	

	public static Activator getDefault() 
	{
		return plugin;
	}


	public static ImageDescriptor getImageDescriptor(String path) 
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
