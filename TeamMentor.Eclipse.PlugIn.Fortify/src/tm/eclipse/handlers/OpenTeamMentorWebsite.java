package tm.eclipse.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import tm.eclipse.ui.PluginPreferences;
import static tm.eclipse.api.EclipseLog.*;

public class OpenTeamMentorWebsite implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException 
	{
		log_Info("in opening teamentor Object execute()");	
		return null;
	}

	@Override
	public boolean isEnabled() 
	{
		return PluginPreferences.showAdvancedMode();
		//return Startup.showDebugViews;
	}

	@Override
	public boolean isHandled() 
	{
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
