package helpers;

import static org.mockito.Mockito.*;

import org.eclipse.jface.preference.IPreferenceStore;

import g2.groovy.api.FortifyAPI;
import g2.java.api.EclipseApi.EclipseAPI;
import g2.scripts.Activator;

public class Activator_Mock 
{	
	static 
	{
		if(Activator.getDefault() == null)
			setup_Activator_Mock();
	}
	
	public static void setup_Activator_Mock()
	{
		Activator.plugin = mock(Activator.class);
		Activator.eclipseApi = mock(EclipseAPI.class);
		Activator.fortifyApi = mock(FortifyAPI.class);
		when(Activator.plugin.getPreferenceStore()).thenReturn(mock(IPreferenceStore.class));
	}
}
