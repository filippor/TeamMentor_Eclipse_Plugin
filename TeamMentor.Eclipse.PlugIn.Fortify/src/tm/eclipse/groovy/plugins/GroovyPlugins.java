package tm.eclipse.groovy.plugins;

import java.net.URL;

import org.apache.commons.io.IOUtils;

import tm.eclipse.ui.Activator;
import tm.eclipse.ui.PluginResources;

public class GroovyPlugins 
{
	public String get_PluginScript_Path(String scriptPath)
	{
		PluginResources pluginResources = new PluginResources(Activator.plugin);
		return pluginResources.get_Resource_Saved_on_TempFolder(scriptPath);
	}
	
	public String get_PluginScript_Code(String scriptPath)
	{
		String path = get_PluginScript_Path(scriptPath);
		if (path == null)
			return null;		
		try 
		{	
			URL url = new URL("file://" + path);
			//URI uri = new URI(path);
			return IOUtils.toString(url);
		}
		catch (Exception e) 
		{		
			e.printStackTrace();
		}
		return null;
	}
	
	public Object execute_PluginScript(String scriptPath)
	{
		String pluginScript = get_PluginScript_Code(scriptPath);
		if (pluginScript == null)
			return null;
		return new GroovyExecution().executeScript(pluginScript);
	}
		
}
