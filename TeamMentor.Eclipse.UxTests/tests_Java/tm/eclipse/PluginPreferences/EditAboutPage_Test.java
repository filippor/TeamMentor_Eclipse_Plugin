package tm.eclipse.PluginPreferences;

import static org.junit.Assert.*;

import org.junit.Test;

import tm.eclipse.groovy.plugins.GroovyExecution;

public class EditAboutPage_Test extends Preferences_JUnit 
{
	@Test
	public void open_EditAboutPage()
	{	
		assertNull(shell);
		openPropertiesPage("tm.eclipse.ui.PluginPreferences.EditAboutPage");
		assertNotNull(shell);
		closePropertiesPage();
	}
	/*@Test
	public void check_Values_EditAboutPage()
	{			
		openPropertiesPage("tm.eclipse.ui.PluginPreferences.EditAboutPage");
		assertNotNull(shell);
		//GroovyExecution.inspect_Object(shell);
		//closePropertiesPage();
	}*/
}
