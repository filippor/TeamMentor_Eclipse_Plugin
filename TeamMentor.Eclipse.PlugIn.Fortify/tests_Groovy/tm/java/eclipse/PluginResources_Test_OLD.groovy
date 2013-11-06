package tm.java.eclipse;

import static org.junit.Assert.*;

import org.eclipse.core.runtime.IPath;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import tm.eclipse.ui.Activator;
import tm.mocks.*;

class PluginResources_Test_OLD 
{	
/*	PluginResources  	  pluginResources;	
	Activator			  plugin;
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder()
	
	@Before
	public void setUp() throws Exception  
	{
		if (Activator.plugin != null)				// for the cases where we are running inside an live RCP/Eclipse instance
		{
		 	plugin = Activator.plugin;
			pluginResources = new PluginResources(plugin);
		}	
		else
		{
			def mockFactory = new Mock_Factory();
			plugin 			= mockFactory.getMocked_Plugin();
			pluginResources = mockFactory.getMocked_PluginResources(plugin);
			plugin.setStateLocation(tempFolder.folder.getAbsolutePath());
		}
	}
	
		
	@Test
	public void PluginResources_Ctor()
	{
		assertNotNull("pluginResources was null"	   , pluginResources);
		assertNotNull("pluginResources.plugin was null", pluginResources.plugin);
		assertNotNull("TempFolder was null"    		   , tempFolder)
		assertTrue   ("TempFolder didn't exist"		   , tempFolder.folder.exists());
	}
	
	@Test
	public void getPluginTempPath()
	{		
		def test = plugin.getStateLocation();		
		def tempPath = pluginResources.getPluginTempPath();				
		assertNotNull("getPluginTempPath returned null", tempPath);
		assertTrue   ("getPluginTempPath folder didn't exists", tempPath.toFile().exists());
	}
	*/
	/*@Test
	void getResourceFile()
	{
		def file = "/plugin.xml";
		//def resource = pluginResources.getResource_String(file);
		
		//assertNull(resource);
	}*/
}
