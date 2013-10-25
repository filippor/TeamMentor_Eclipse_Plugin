package teammentor.eclipse.plugin.fortify.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import g2.scripts.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 	
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_OPEN_ARTICLE_NEW_WINDOW, false);
		store.setDefault(PreferenceConstants.P_TEAMMENTOR_SERVER, "https://vulnerabilities.teammentor.net");
		store.setDefault(PreferenceConstants.P_TEAMMENTOR_SESSION_ID,"00000000-0000-0000-0000-000000000001");		
	}

}
