package tm.eclipse.ui;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Test;

import tm.eclipse.mock.Activator_Mock;
import tm.eclipse.ui.Activator;
import tm.eclipse.ui.PreferenceInitializer;

public class PreferenceInitializer_Test extends Activator_Mock
{

	@Test
	public void initializeDefaultPreferences_Test() 
	{	
		PreferenceInitializer preferenceInitializer = new PreferenceInitializer();				
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		assertNotNull(preferenceInitializer);
		assertNotNull(store);
		
		preferenceInitializer.initializeDefaultPreferences();

		verify(store).setDefault(PreferenceInitializer.P_TEAMMENTOR_SERVER, "https://vulnerabilities.teammentor.net");
		verify(store).setDefault(PreferenceInitializer.P_TEAMMENTOR_SESSION_ID, "00000000-0000-0000-0000-000000000000");
		verify(store).setDefault(PreferenceInitializer.P_OPEN_ARTICLE_NEW_WINDOW, false);
		verify(store).setDefault(PreferenceInitializer.P_TEAMMENTOR_ADVANCED_MODE, false);
		
	}

}
