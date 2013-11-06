package helpers;

import static org.mockito.Mockito.*;

import org.eclipse.jface.preference.IPreferenceStore;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.groovy.FortifyAPI;
import tm.eclipse.ui.Activator;

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
