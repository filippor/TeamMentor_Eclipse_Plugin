package tests.g2.java.api;

import static org.junit.Assert.*;
import g2.java.api.EclipseAPI;

import org.junit.Test;

public class Test_EclipseAPI {

	@Test
	public void captureEclipseObjects() 
	{
		EclipseAPI eclipseAPI = new EclipseAPI();
		assertNotNull(eclipseAPI);
		assertNotNull(eclipseAPI.workbench);
		assertNotNull(eclipseAPI.activeWorkbenchWindow);
		assertNotNull(eclipseAPI.display);
		assertNotNull(eclipseAPI.shell);
		assertNotNull(eclipseAPI.testGroovy);
		assertNotNull(eclipseAPI.workspace);
	}

}
