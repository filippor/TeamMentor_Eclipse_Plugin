package aa.swtbot; //aa so that it runs first

import static org.junit.Assert.*;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;

public class SWTBot_Setup 
{

	public static SWTWorkbenchBot	bot;
		
	public SWTBot_Setup()
	{
		bot = new SWTWorkbenchBot();
	}
		
	@Test
	public void SWTWorkbenchBot_OK() 
	{
		assertNotNull(bot);
	}

	@Test
	public void Close_Welcome_View()
	{
		try
        {
			bot.viewByTitle("Welcome").close();
        }
        catch(WidgetNotFoundException ex) {}
		
	}
}
