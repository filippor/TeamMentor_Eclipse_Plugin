package tm.eclipse.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class EclipseUI_Test 
{
	@Test
	public void EclipseUI_Ctor()
	{
		EclipseAPI eclipseAPI = new EclipseAPI();
		EclipseUI  eclipseUi  = new EclipseUI(eclipseAPI.workbench);
		assertNotNull(eclipseUi);
		assertNotNull(eclipseUi.activePage());
		assertNotNull(eclipseUi.activeWorkbenchWindow());
	}
}
