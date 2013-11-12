package tm.swtbot.helpers;

import java.util.ArrayList;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.finders.WorkbenchContentsFinder;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import tm.swtbot.SWTBot_Consts;
import tm.swtbot.models.SWTBot_View;

public class SWTBot_Views 
{
	public boolean 		    LOG_EXCEPTION_VIEW_NOT_FOUND;	
	public SWTWorkbenchBot	bot;
	
	public SWTBot_Views(SWTWorkbenchBot _bot) 
	{
		bot = _bot;
		LOG_EXCEPTION_VIEW_NOT_FOUND = false;
	}
		
	public ArrayList<SWTBot_View> get_Views()
	{
		ArrayList<SWTBot_View> views = new ArrayList<SWTBot_View>();
		for(SWTBotView swtBotView : bot.views())
		{
			views.add(new SWTBot_View(swtBotView));
		}
		return views;
	}	
	public SWTBotView 			  get_View_Fast(String viewNameOrId)
	{
		long originalTimeout = get_Timeout();			// save current value
		set_Timeout(SWTBot_Consts.TIMEOUT_FAST);		// set to TIMEOUT_FAST
		SWTBotView view = get_View(viewNameOrId);		// get view
		set_Timeout(originalTimeout);					// reset original value
		return view;
	}
	public SWTBotView 			  get_View(String viewNameOrId)
	{
		SWTBotView view = null;
		
		try
		{
			view =  bot.viewByTitle(viewNameOrId);
		}
		catch(Exception ex)
		{	
			if (LOG_EXCEPTION_VIEW_NOT_FOUND)
				ex.printStackTrace();
		}
		if(view == null)
		{
			try
			{
				view =  bot.viewById(viewNameOrId);
			}
			catch(Exception ex)
			{	
				if (LOG_EXCEPTION_VIEW_NOT_FOUND)
					ex.printStackTrace();
			}
		}
	
		return view;
	}
	public long 				  get_Timeout()
	{
		return SWTBotPreferences.TIMEOUT;
	}
	public SWTBotView			  open_View(String primaryId)
	{
		return open_View(primaryId, null);
	}
	public SWTBotView			  open_View(final String primaryId, String secundaryId)
	{
		SWTBotView swtBotView = get_View_Fast(primaryId);
		if (swtBotView != null)
			return swtBotView;
		
		UIThreadRunnable.syncExec(new VoidResult() {
			public void run() {
				try {
									
						IWorkbenchWindow workbenchWindow = new WorkbenchContentsFinder().activeWorkbenchWindow();
						workbenchWindow.getActivePage().showView(primaryId);
					}
					catch (PartInitException e) 
					{
						e.printStackTrace();
					}
				}});
		return get_View(primaryId);
	}
	public SWTBot_Views 		  set_Timeout(long value)
	{
		SWTBotPreferences.TIMEOUT = value;
		return this;
	}
}
