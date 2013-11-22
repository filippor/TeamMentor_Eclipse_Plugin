package tm;

import static org.junit.Assert.*;

import org.eclipse.ui.IViewReference;
import org.junit.Test;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.ui.Startup;
import tm.tests.helpers.Eclipse_Consts;

public class First_Test 
{
	@Test
	public void closeAboutWindow()
	{
		EclipseAPI eclipseAPI = Startup.eclipseApi;
		IViewReference viewReference = eclipseAPI.views.get_View_Reference(Eclipse_Consts.VIEW_ID_WELCOME_SCREEN);
		if (viewReference != null)
		{
			eclipseAPI.views.close(viewReference);
			viewReference = eclipseAPI.views.get_View_Reference(Eclipse_Consts.VIEW_ID_WELCOME_SCREEN);
			assertNull(viewReference);
		}
	}

}
