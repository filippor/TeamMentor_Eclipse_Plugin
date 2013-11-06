package tm.mocks

import g2.java.api.EclipseApi.EclipseAPI
import g2.scripts.Activator;
import groovy.mock.interceptor.*;
import helpers.Activator_Mock

import org.eclipse.core.runtime.*
import org.junit.rules.*
import org.junit.*;
import tm.eclipse.ui.*

public class Mock_Factory 
{
	public static String DEFAULT_STATE_LOCATION = "/test/statelocation"
	
	public Activator getMocked_Plugin()
	{
		def stateLocation = DEFAULT_STATE_LOCATION;
		def plugin = new Activator_Mock().plugin;
		
		
		plugin.metaClass.getStateLocation = 
			{
				return new Path(stateLocation);
			}
			
		plugin.metaClass.setStateLocation =
			{	newLocation ->	
			  l:{			
					stateLocation = newLocation
					return plugin.getStateLocation()
				}
			}
			
		return plugin;	
	}
	
	public PluginResources getMocked_PluginResources(Activator plugin)
	{
		def pluginResources = new PluginResources(plugin);		
		pluginResources.get_Plugin_TempPath();
		pluginResources.metaClass.get_Plugin_TempPath = 
			{
				return plugin.getStateLocation();			
			}
		return pluginResources;
	}
}
 