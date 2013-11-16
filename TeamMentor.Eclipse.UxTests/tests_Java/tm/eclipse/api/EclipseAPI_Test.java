package tm.eclipse.api;

import static org.junit.Assert.*;
import org.junit.Test;
import tm.eclipse.api.EclipseAPI;

public class EclipseAPI_Test 
{
        public EclipseAPI eclipseAPI = new EclipseAPI();
        
        @Test
        public void ctor()
        {                
                assertNotNull(eclipseAPI);
                assertNotNull(eclipseAPI.menus);
                assertNotNull(eclipseAPI.menus.workbench);
                assertNotNull(eclipseAPI.panels);                
                assertNotNull(eclipseAPI.panels.workbench);
                assertNotNull(eclipseAPI.views);
                assertNotNull(eclipseAPI.views.workbench);
        }
        
        @Test
        public void captureEclipseObjects() 
        {                
                assertNotNull(eclipseAPI);
                assertNotNull(eclipseAPI.workbench);
                assertNotNull(eclipseAPI.activeWorkbenchWindow);
                assertNotNull(eclipseAPI.display);
                assertNotNull(eclipseAPI.shell);
                assertNotNull(eclipseAPI.workspace);
        }

}