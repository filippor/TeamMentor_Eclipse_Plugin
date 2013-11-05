package helpers;

import static org.mockito.Mockito.*;

import org.eclipse.jface.preference.IPreferenceStore;
import g2.groovy.api.FortifyAPI;
import g2.java.api.EclipseApi.EclipseAPI;
import g2.scripts.Activator;

public class Activator_Mock 
{	
	public static Activator plugin;
	
	static 
	{
		if(Activator.getDefault() == null)
			setup_Activator_Mock();
	}
	
	public static void setup_Activator_Mock()
	{
		plugin = mock(Activator.class);
		Activator.plugin = plugin;
		Activator.eclipseApi = mock(EclipseAPI.class);
		Activator.fortifyApi = mock(FortifyAPI.class); 
		when(Activator.plugin.getPreferenceStore()).thenReturn(mock(IPreferenceStore.class));
		
		// Activator.plugin.getStateLocation(); // cant be done here because getStateLocation is final
	
	}
}
