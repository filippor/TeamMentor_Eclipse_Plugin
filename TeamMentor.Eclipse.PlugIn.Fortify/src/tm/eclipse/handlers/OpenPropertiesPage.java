package tm.eclipse.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.PreferencesUtil;
import tm.eclipse.ui.Startup;

public class OpenPropertiesPage implements IHandler 
{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException 
	{		
		IWorkbench workbench = Startup.eclipseApi.workbench;
		PreferenceDialog preferenceDialog = PreferencesUtil.createPreferenceDialogOn(workbench.getActiveWorkbenchWindow().getShell(), "teammentor.eclipse.plugin.fortify.preferences.PluginPreferences", null, null);
		preferenceDialog.open();
		return null;
	}

	@Override public boolean isEnabled()  { return true; }
	@Override public boolean isHandled()  { return true; }	
	@Override public void 	 dispose() 											     { }
	@Override public void 	 addHandlerListener(IHandlerListener handlerListener)    { }
	@Override public void 	 removeHandlerListener(IHandlerListener handlerListener) { }

}
