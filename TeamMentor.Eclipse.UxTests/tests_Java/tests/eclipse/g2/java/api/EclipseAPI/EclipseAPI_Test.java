package tests.eclipse.g2.java.api.EclipseAPI;

import static org.junit.Assert.*;
import g2.java.api.EclipseApi.EclipseAPI;

import org.junit.Test;

public class EclipseAPI_Test 
{
        EclipseAPI eclipseAPI = new EclipseAPI();
        
        @Test
        public void ctor()
        {                
                assertNotNull(eclipseAPI);
                assertNotNull(eclipseAPI.menus);
                assertNotNull(eclipseAPI.menus.workbench);
                assertNotNull(eclipseAPI.panels);
                assertNotNull(eclipseAPI.panels.workbench);
        }
        
        @Test
        public void captureEclipseObjects() 
        {                
                assertNotNull(eclipseAPI);
                assertNotNull(eclipseAPI.workbench);
                assertNotNull(eclipseAPI.activeWorkbenchWindow);
                assertNotNull(eclipseAPI.display);
                assertNotNull(eclipseAPI.shell);
                assertNotNull(eclipseAPI.testGroovy);
                assertNotNull(eclipseAPI.workspace);
        }

}