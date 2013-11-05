package tm.mocks

import static org.junit.Assert.*;
import g2.scripts.Activator;

import org.eclipse.core.runtime.IPath
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

public class Mock_Factory_Test
{	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();
	
	@Test
	public void getMocked_Activator()
	{				
		def plugin = new Mock_Factory().getMocked_Plugin();
		assertNotNull(plugin);
	 
	 // testgetStateLocation_Test()	
		IPath stateLocation = plugin.getStateLocation();
		assertNotNull(stateLocation);
		assertEquals(stateLocation.toString(), Mock_Factory.DEFAULT_STATE_LOCATION)
		String absolutePath = tempFolder.folder.getAbsolutePath();
		plugin.setStateLocation(absolutePath);		
		assertEquals(plugin.getStateLocation().toString(), absolutePath);		
	}
	
}
