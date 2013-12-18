package tm.eclipse.helpers;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.*;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.*;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.dialogs.PreferencesUtil;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.swt.controls.Browser_Ex;
import tm.eclipse.swt.controls.Form_Ex;
import tm.eclipse.ui.Startup;
import tm.eclipse.ui.views.Eclipse_Panel;

public class eclipseUI 
{
	public static EclipseAPI eclipse;
	
	static
	{
		eclipse = Startup.eclipseApi;
	}
	
	public static Eclipse_Panel 		 add_View(String viewID)
	{
		return new_View(viewID);
	}
	public static void 					 asyncExec(final Runnable runnable)
	{
		UIThreadRunnable.asyncExec(new VoidResult() { public void run()
			{
				runnable.run();
			}});
		
	}
	public static IEditorReference 		 editor(String title)
	{
		return get_Editor(title);
	}
	public static List<IEditorReference> editors()
	{
		return get_Editors();
	}
	public static IEditorReference 		 get_Editor(String title)
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
	public static IViewReference 		 get_View(String titleOrId)
	{
		for(IViewReference editor : get_Views())
			if(editor.getTitle().equals(titleOrId) || editor.getId().equals(titleOrId))
					return editor;
		return null;
	}	public static List<IViewReference> 	 get_Views()
	{
		return Arrays.asList(eclipse.activeWorkbenchPage.getViewReferences());
	}
	public static boolean				 hasView(String viewId)
	{
		return view(viewId) != null;
	}
	public static String 				 messageBox(final String message)
	{
		return messageBox(message, "");
	}
    public static String 				 messageBox(final String message, final String title)
    {
    	syncExec(new Runnable() { public void run() 
    		{
    			MessageBox messageBox = new MessageBox(eclipse.shell);
    			messageBox.setMessage(message);
    			messageBox.setText(title);
    			messageBox.open();
    		}});
    	return message;
    }
	public static Eclipse_Panel 		 new_Panel()
	{
		return eclipse.panelFactory.open_Panel();
	}
	public static Eclipse_Panel 		 new_Panel(String panelId)
	{
		return eclipse.panelFactory.open_Panel(panelId);
	}
	public static Browser_Ex			 new_Browser()
	{
		return new_Browser(null);
	}
	public static Browser_Ex			 new_Browser(String browserId)
	{
		return new_Panel(browserId).add_Browser();
	}
	public static Eclipse_Panel 		 new_View(String viewID)
	{
		return new_Panel(viewID);
	}
	public static PreferenceDialog 		 open_PreferencesDialog(final String id)
	{		
		return UIThreadRunnable.syncExec(new Result<PreferenceDialog>() { public PreferenceDialog run() 
				{
					PreferenceDialog preferenceDialog = PreferencesUtil.createPreferenceDialogOn(eclipse.workbench.getActiveWorkbenchWindow().getShell(), id, null, null);
					preferenceDialog.open();
					return preferenceDialog;		
				}});
		
	}
	public static Eclipse_Panel 		 open_View(String viewID)
	{
		return new_View(viewID);
	}
	public static Form_Ex 				 popupWindow()
	{
		return Form_Ex.popupWindow();
	}
	public static void 					 syncExec(final Runnable runnable)
	{
		UIThreadRunnable.syncExec(new VoidResult() { public void run()
			{
				runnable.run();
			}});
		
	}
	public static IViewReference 		 view(String title)
	{
		return get_View(title);
	}	

	public static List<IViewReference>   views()
	{
		return get_Views();
	}
}
