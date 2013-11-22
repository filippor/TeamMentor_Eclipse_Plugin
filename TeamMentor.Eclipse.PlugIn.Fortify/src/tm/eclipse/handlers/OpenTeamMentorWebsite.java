package tm.eclipse.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IWorkbench;

import tm.eclipse.api.Panels;
import tm.eclipse.ui.Startup;

public class OpenTeamMentorWebsite implements IHandler 
{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException 
	{
		IWorkbench workbench = Startup.eclipseApi.workbench;
		final Panels panels = new Panels(workbench);	
		
		panels.open_Url_in_WebBrowser("teammentor.net","http://teammentor.net");	
		return null;
	}

	@Override public boolean isEnabled()  { return true; }
	@Override public boolean isHandled()  { return true; }	
	@Override public void 	 dispose() 											     { }
	@Override public void 	 addHandlerListener(IHandlerListener handlerListener)    { }
	@Override public void 	 removeHandlerListener(IHandlerListener handlerListener) { }
}
