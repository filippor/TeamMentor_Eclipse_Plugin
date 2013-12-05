package tm.eclipse.helpers;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.dialogs.PreferencesUtil;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.ui.Startup;

public class eclipseUI 
{
	public static EclipseAPI eclipse;
	
	static
	{
		eclipse = Startup.eclipseApi;
	}
	public static PreferenceDialog open_PreferencesDialog(final String id)
	{		
		return syncExec(new Result<PreferenceDialog>() { public PreferenceDialog run() 
				{
					PreferenceDialog preferenceDialog = PreferencesUtil.createPreferenceDialogOn(eclipse.workbench.getActiveWorkbenchWindow().getShell(), id, null, null);
					preferenceDialog.open();
					return preferenceDialog;		
				}});
		
	}
	public static IEditorReference get_Editor(String title)
	{
		for(IEditorReference editor : get_Editors())
			if(editor.getTitle().equals(title))
					return editor;
		return null;
	}
	public static List<IEditorReference> get_Editors()
	{
		return Arrays.asList(eclipse.activeWorkbenchPage.getEditorReferences());
	}
	public static IViewReference get_View(String title)
	{
		for(IViewReference editor : get_Views())
			if(editor.getTitle().equals(title))
					return editor;
		return null;
	}
	public static List<IViewReference> get_Views()
	{
		return Arrays.asList(eclipse.activeWorkbenchPage.getViewReferences());
	}
	public static IEditorReference editor(String title)
	{
		return get_Editor(title);
	}
	public static List<IEditorReference> editors()
	{
		return get_Editors();
	}
	public static IViewReference view(String title)
	{
		return get_View(title);
	}
	public static List<IViewReference> views()
	{
		return get_Views();
	}
}
