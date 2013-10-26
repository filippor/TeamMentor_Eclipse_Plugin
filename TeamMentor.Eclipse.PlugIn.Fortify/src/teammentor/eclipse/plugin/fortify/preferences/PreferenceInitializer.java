package teammentor.eclipse.plugin.fortify.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import g2.scripts.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer 
{
	
	public static final String P_OPEN_ARTICLE_NEW_WINDOW 	= "P_OPEN_ARTICLE_NEW_WINDOW";
	public static final String P_TEAMMENTOR_SERVER 		 	= "P_TEAMMENTOR_SERVER";
	public static final String P_TEAMMENTOR_SESSION_ID   	= "P_TEAMMENTOR_SESSION_ID";
	public static final String P_TEAMMENTOR_ADVANCED_MODE   = "P_TEAMMENTOR_ADVANCED_MODE";
	
	public void initializeDefaultPreferences() 
	{		
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(P_OPEN_ARTICLE_NEW_WINDOW, false);
		store.setDefault(P_TEAMMENTOR_SERVER, "https://vulnerabilities.teammentor.net");
		store.setDefault(P_TEAMMENTOR_SESSION_ID,"00000000-0000-0000-0000-000000000000");
		store.setDefault(P_TEAMMENTOR_ADVANCED_MODE,false);
	}

}
