package tm.eclipse.api;

import java.util.Arrays;
import java.util.List;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;

public class Views extends EclipseUI
{		
	public Views(IWorkbench workbench)
	{
		super(workbench);
	}
	
	public List<IViewReference> get_Views_References()
	{
		IViewReference[] items = activePage().getViewReferences();
		return Arrays.asList(items);
	}
	
	public IViewPart open_View(String viewId)
	{
		try
		{
			return activePage().showView(viewId);
		}
		catch(Exception ex)
		{
			System.out.println("[open_View] Could not find view: " + viewId);
		}
		return null;
	}	
	
}
