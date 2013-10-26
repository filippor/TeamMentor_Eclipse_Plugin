package g2.groovy.api.FortifyAPI_Test;

import static org.junit.Assert.assertNotNull;
import g2.scripts.Activator;
import helpers.Activator_Mock;

import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Test;
public class Activator_Test extends Activator_Mock
{
	@Test
	public void Activator_Ctor_Test()
	{				
		assertNotNull(Activator.plugin);
		assertNotNull(Activator.getDefault());
				
		assertNotNull(Activator.eclipseApi);
		assertNotNull(Activator.fortifyApi);			
	}
	
	@Test
	public void PreferenceStore_Test()
	{		
		Activator activator = Activator.getDefault();
		assertNotNull(activator);
		IPreferenceStore store = activator.getPreferenceStore();
		assertNotNull(store);
	}
}
