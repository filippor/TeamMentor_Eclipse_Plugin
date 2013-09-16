package g2.scripts;

import g2.java.api.TeamMentorMenu;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin 
{
	public static final String PLUGIN_ID = "g2.scripts"; 

	private static Activator plugin;
	

	public Activator() { }

	public void start(BundleContext context) throws Exception 
	{
		super.start(context);
		
		TeamMentorMenu.createTeamMentorMenu();
		plugin = this;
	}
	
	public void stop(BundleContext context) throws Exception 
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
