package tm.eclipse.ui.PluginPreferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

import tm.eclipse.ui.Activator;

public class MainPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	
	public MainPreferences() 
	{
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("\nPlease use the fields below to configure the TeamMentor behaviour,server and account\n\n");
	}
		
	public void createFieldEditors() 
	{		
		addField(new StringFieldEditor (PreferenceInitializer.P_TEAMMENTOR_SERVER		 , "TeamMentor &Server:"					, getFieldEditorParent()));
		addField(new StringFieldEditor (PreferenceInitializer.P_TEAMMENTOR_SESSION_ID	 , "TeamMentor &SessionId:"					, getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceInitializer.P_OPEN_ARTICLE_NEW_WINDOW	 , "&Open TeamMentor article in new window"	, getFieldEditorParent()));		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_TEAMMENTOR_LOAD_PLUGINS	 , "&Load Plugins on Startup"				, getFieldEditorParent()));		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_TEAMMENTOR_ADVANCED_MODE , "&Show Advanced Mode Features"			, getFieldEditorParent()));
	}
	
	public void init(IWorkbench workbench) { }
	
	public static boolean openArticleInNewWindow()
	{			
		return store.getBoolean(PreferenceInitializer.P_OPEN_ARTICLE_NEW_WINDOW);
	}
	public static boolean showAdvancedMode()
	{			
		return store.getBoolean(PreferenceInitializer.P_TEAMMENTOR_ADVANCED_MODE);
	}
	public static boolean loadPluginsOnStartup()
	{			
		return store.getBoolean(PreferenceInitializer.P_TEAMMENTOR_LOAD_PLUGINS);
	}
	public static String getServer()
	{
		return store.getString(PreferenceInitializer.P_TEAMMENTOR_SERVER);
	}
	public static void setServer(String value)
	{
		store.setValue(PreferenceInitializer.P_TEAMMENTOR_SERVER,value);
	}
	public static String getSessionId()
	{
		return store.getString(PreferenceInitializer.P_TEAMMENTOR_SESSION_ID);
	}
	public static String getAboutHtml()
	{
		return store.getString(PreferenceInitializer.P_TEAMMENTOR_ABOUT_HTML);
	}
	public static void setAboutHtml(String value)
	{
		store.setValue(PreferenceInitializer.P_TEAMMENTOR_ABOUT_HTML,value);
	}
	public static void setSessionId(String value)
	{
		store.setValue(PreferenceInitializer.P_TEAMMENTOR_SESSION_ID, value);		
	}
	public static String getDefaultBrowserId()
	{
		return "TeamMentor Content";		
	}
	public static String getDefaultBrowserTitle()
	{
		return "TeamMentor";		
	}
	
}