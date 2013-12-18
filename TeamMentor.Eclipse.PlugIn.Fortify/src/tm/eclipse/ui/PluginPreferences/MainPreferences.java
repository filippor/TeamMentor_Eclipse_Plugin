package tm.eclipse.ui.PluginPreferences;

import groovy.inspect.Inspector;
import groovy.inspect.swingui.ObjectBrowser;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

import tm.eclipse.api.Console;
import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.ui.Activator;
import tm.eclipse.ui.Startup;
import tm.utils.Consts_TM;

public class MainPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	
	public MainPreferences() 
	{
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		//setDescription("\nPlease use the fields below to configure the TeamMentor behaviour,server and account\n\n");
	}
		
	public void createFieldEditors() 
	{	
		final Composite parent = getFieldEditorParent();
		//parent.setLayout(new GridLayout(2, false));	
		String serverTitle = "AATo access password protected instances of TeamMentor you need to provide a valid Login Token.\n\n" + 
				             "Please use the fields below to set the target server and token";
		
		new Label(parent,SWT.BORDER).setText(serverTitle);
		
		
		Group server_Config = new Group(parent, SWT.NONE);		
		server_Config.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		addField(new StringFieldEditor (PreferenceInitializer.P_TEAMMENTOR_SERVER		 , "TeamMentor &Server:", server_Config));
		final StringFieldEditor loginToken = new StringFieldEditor (PreferenceInitializer.P_TEAMMENTOR_SESSION_ID	 , "Login &Token:"		, server_Config);				
		addField(loginToken);
									
//		new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL)
//			 .setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
	
		// Login form
		
		String groupTitle = "To get a token You can use the form below to login into the current TeamMentor server (set above)";
		new Label(parent,SWT.BORDER).setText(groupTitle);
		
		
		Group group = new Group(parent, SWT.NONE);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gridData.verticalAlignment = 20;
		group.setLayoutData(gridData);
		
		group.setLayout(new GridLayout(2, false));	
					
		new Label(group,SWT.BORDER).setText("User name:");
		
		final Text username_Text = new Text(group,SWT.BORDER);
		username_Text.setText("");
		username_Text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new Label(group,SWT.BORDER).setText("Password");
		
		final Text password_Text = new Text(group,SWT.BORDER | SWT.PASSWORD);
		password_Text.setText("");
		password_Text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//new Label(group,SWT.BORDER);
		final Label result  = new Label(group, SWT.WRAP);
		Button login_Button = new Button(group, SWT.NONE);		
		login_Button.setText("Login and get token");
		
		//new Label(group,SWT.BORDER);

		
		result.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,false,false,1,1)); // needed or the setText below will not show		
	
		// SSO form
		
			String ssoTitle = "If you have access to a SSO token (Sign-sign on), please use the form below";
			new Label(parent,SWT.BORDER).setText(ssoTitle);
			
			
			Group sso_Group = new Group(parent, SWT.NONE);
			sso_Group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
			
			sso_Group.setLayout(new GridLayout(2, false));	
						
			new Label(sso_Group,SWT.BORDER).setText("SSO Token");
			final Text sso_Token = new Text(sso_Group,SWT.BORDER | SWT.PASSWORD);
			sso_Token.setText("");
			sso_Token.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			
			new Label(sso_Group,SWT.BORDER);
			Button sso_Button = new Button(sso_Group, SWT.NONE);		
			sso_Button.setText("Authenticate");
		
			
		new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL)
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		// config options
			
		new Label(parent,SWT.BORDER).setText("Misc Plugin Config Options");
		
		Group group_Config = new Group(parent, SWT.NONE);		
		group_Config.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_OPEN_ARTICLE_NEW_WINDOW	 , "&Open TeamMentor article in new window"	, group_Config));		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_TEAMMENTOR_LOAD_PLUGINS	 , "&Load Plugins on Startup"				, group_Config));		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_TEAMMENTOR_ADVANCED_MODE , "&Show Advanced Mode Features"			, group_Config));

		
		//button action
		login_Button.addListener(SWT.Selection, new Listener() 
	  	{	
			public void handleEvent(Event event) 
			{	
				result.setText("Logging in...");
				parent.layout(true);
				final String username = username_Text.getText();
				final String password = password_Text.getText();
				new Thread(new Runnable() { public void run() 
					{
						final String sessionId = TeamMentorAPI.loginIntoTeamMentor(username, password);
						UIThreadRunnable.syncExec(new VoidResult() { public void run() 
							{
								if (sessionId == null || sessionId.equals("") || sessionId.equals(Consts_TM.EMPTY_GUID))
								{
									logMessage("Login failed");									
									result.setText(Consts_TM.MSG_LOGIN_FAILED);
								}
								else
								{
									logMessage("Login Ok");
									result.setText(Consts_TM.MSG_LOGIN_OK);
									setSessionId(sessionId);
									updateApplyButton();
									loginToken.load();
								}	
							}});							
					}}).start();
			}
		});
			
	}
	
	public void logMessage(String message)
	{
		String consoleName = "TeamMentor Scripts"; 
		Console console = Startup.eclipseApi.console;
		console.new_MessageConsole(consoleName);
		//console.get(consoleName).clearConsole();			// use to clear
		console.write(consoleName, message);

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