package tm.swtbot;

import static org.junit.Assert.*;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.junit.Test;

public class SWTBot_JUnit_Test 
{
	SWTBot_JUnit swtbotJunit = new SWTBot_JUnit();
	
	@Test
	public void SWTBot_JUnit_Ctor()
	{
		assertNotNull(swtbotJunit);
		SWTBotView welcome = swtbotJunit.views.get_View_Fast(SWTBot_Consts.VIEW_ID_IntroView);
		assertNull(welcome);
	}
}
