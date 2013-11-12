package tm.swtbot; //aa so that it runs first

import static org.junit.Assert.*;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;

public class SWTBot_JUnit extends SWTBot_Helpers
{

	public SWTBot_JUnit()
	{
		Close_Welcome_View();
	}
		
	//@Test
	public void SWTWorkbenchBot_OK() 
	{
		assertNotNull(bot);
	}

	public SWTBot_JUnit Close_Welcome_View()
	{
		SWTBotView introView = views.get_View_Fast(SWTBot_Consts.VIEW_ID_IntroView);
		if (introView != null)
			introView.close();
		return this;		
	}
}
