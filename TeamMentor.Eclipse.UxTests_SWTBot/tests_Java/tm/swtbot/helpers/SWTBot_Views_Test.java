package tm.swtbot.helpers;

import static org.junit.Assert.*;

import java.util.Random;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.Test;

import tm.swtbot.SWTBot_Consts;
import tm.swtbot.SWTBot_Helpers;
import tm.swtbot.helpers.SWTBot_Views;
import tm.swtbot.models.SWTBot_View;

public class SWTBot_Views_Test 
{
	SWTBot_Helpers helper;
	SWTBot_Views   views;
	
	public SWTBot_Views_Test()
	{
		helper = new SWTBot_Helpers();
		views  = helper.views;
	}
	@Test 
	public void SWTBot_Views_Test_Ctor()
	{	
		assertNotNull(helper);
		assertNotNull(helper.bot);
		assertNotNull(views);
		assertNotNull(views.bot);
		assertEquals (views.bot, helper.bot);
	}
	
	@Test 
	public void getset_timeout()
	{		
		long originalTimeout = SWTBotPreferences.TIMEOUT;
		long newTimeout 	 = Math.abs(new Random().nextLong());
		
		assertEquals	 (originalTimeout,views.get_Timeout());
		assertNotEquals  (newTimeout     ,views.get_Timeout());
		
		views.set_Timeout(newTimeout);
		assertNotEquals	 (originalTimeout,views.get_Timeout());
		assertEquals 	 (newTimeout     ,views.get_Timeout());
		
		views.set_Timeout(originalTimeout);
		assertNotEquals  (newTimeout     ,views.get_Timeout());
		assertEquals	 (originalTimeout,views.get_Timeout());
		
	}
		
	@Test
	public void get_view()
	{	
		//this one shouldn't exist
		SWTBotView view_ShouldNotExist = views.get_View_Fast("Welcome__ABC");
		assertNull(view_ShouldNotExist);
				
		//if this one exists, get it and close it
		SWTBotView view_ShouldExist    = views.get_View_Fast("Welcome");
		if (view_ShouldExist!=null)
		{
			assertNotNull(view_ShouldExist);
			view_ShouldExist.close();
		}				
		
		//getting the views 
		view_ShouldExist    = views.get_View_Fast("Welcome");
		view_ShouldNotExist = views.get_View_Fast("Welcome__ABC");		
		
		assertNull(view_ShouldExist);
		assertNull(view_ShouldNotExist);			
	}
	
	@Test
	public void get_views()
	{				
		for(SWTBot_View view : views.get_Views())			
		{
			assertNotNull(view);			
			assertNotNull(view.id);
			assertNotNull(view.name);
			assertNotNull(view.swtBotView);
			assertNotNull(view.title);
			assertNotNull(view.viewPart);
			assertNotNull(view.viewReference);
			assertNotNull(view.viewSite);
			assertNotNull(view.widget);
			assertNotNull(view.workbenchPart);
		}
	}
	
	@Test
	public void open_View()
	{		
		String intro_View_Id = SWTBot_Consts.VIEW_ID_IntroView;
		
		//if it is already open, get the reference and close it
		SWTBotView  intro_View 	  = views.get_View_Fast(intro_View_Id);
		if (intro_View!=null)
		{
			assertNotNull(intro_View);
			intro_View.close();
			intro_View 	  = views.get_View_Fast(intro_View_Id);
		}
		assertNull(intro_View);
				
		//helper.addDummyTaskToWorkspace();
		
		//now open it 
		SWTBotView 	  intro_View_after_Open = views.open_View(intro_View_Id);		
		assertNotNull(intro_View_after_Open );
		SWTBotView 	  intro_View_after_Get  = views.get_View_Fast(intro_View_Id);
		assertNotNull(intro_View_after_Get  );
		
		//and close it again
		intro_View_after_Open.close();
		SWTBotView 	intro_View_after_Close  = views.get_View_Fast(intro_View_Id);
		assertNull( intro_View_after_Close);
	}

}

