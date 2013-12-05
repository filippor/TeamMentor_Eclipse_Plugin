package tm.eclipse.PluginPreferences;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LoginToTM_Test extends Preferences_JUnit
{
	@Test
	public void open_EditAboutPage()
	{	
		assertNull(shell);
		openPropertiesPage("tm.eclipse.ui.PluginPreferences.LoginToTM");
		assertNotNull(shell);
		//closePropertiesPage();
	}
	
}
