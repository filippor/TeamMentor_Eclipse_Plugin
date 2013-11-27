package tm.eclipse.api;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import org.eclipse.swtbot.swt.finder.results.Result;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;

import tm.eclipse.ui.Startup;

public class Views extends EclipseUI
{		
	public Views(IWorkbench workbench)
	{
		super(workbench);
	}
	
	public List<IViewReference> get_Views_References()
	{
		return syncExec(new Result<List<IViewReference>>() { public List<IViewReference> run ()  
			{
				IViewReference[] items = activePage.getViewReferences();
				return Arrays.asList(items);
				}});
	}
	
	public IViewReference get_View_Reference(final String viewId) 
	{
		return syncExec(new Result<IViewReference>() { public IViewReference run ()  
			{
			 	IViewReference viewReference =  activePage.findViewReference(viewId);
				if (viewReference!=null)
					return viewReference;
				return get_View_Reference_by_Id(viewId);			
			}});
	}
	
	public IViewReference get_View_Reference_by_Id(final String viewId) 
	{
		return syncExec(new Result<IViewReference>() { public IViewReference run ()  
		{		 	
			//search by Id
			for(IViewReference viewReference : get_Views_References())
			{
				String id = viewReference.getId(); 
				if (id.equals(viewId))  
					return viewReference;
			}
			for(IViewReference viewReference : get_Views_References())
			{
				String title = viewReference.getTitle(); 
				if (title.equals(viewId))  
					return viewReference;				
			}
			String a = "";
			return null;					
		}});
	}
	public IViewPart open_View(final String viewId)
	{
		return syncExec(new Result<IViewPart>() { public IViewPart run() 
			{
				try 
				{
					return activePage.showView(viewId);
				} catch (PartInitException e) 
				{				
					System.out.println("[open_View] Could not find view: " + viewId);
					//e.printStackTrace();
					return null;
				}
			}});
		
		/*try
		{
			return activePage().showView(viewId);
		}
		catch(Exception ex)
		{
			System.out.println("[open_View] Could not find view: " + viewId);
		}
		return null;*/
	}

	public <T extends IViewReference>T close(final T viewToClose)
	{
		if (viewToClose!=null)			 
			close(open_View(viewToClose.getId()));
		return viewToClose;
	}	
	
	public <T extends IViewPart>T close(final T viewToClose)
	{
		if (viewToClose != null)
		{
			syncExec(new VoidResult() { public void run() 
				{			
					Startup.eclipseApi.activeWorkbenchPage.hideView(viewToClose);
				}});
		}
		return viewToClose;
	}	
	
}
