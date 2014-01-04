package tm.eclipse.ui.pluginPreferences.pages;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jface.preference.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.*;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;

import tm.eclipse.api.Console;
import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.helpers.colors;
import tm.eclipse.swt.controls.Button;
import tm.eclipse.swt.controls.Label;
import tm.eclipse.swt.controls.Text;
import tm.eclipse.ui.Activator;
import tm.eclipse.ui.Startup;
import tm.eclipse.ui.pluginPreferences.PreferenceInitializer;
import tm.eclipse.ui.pluginPreferences.TM_Preferences;
import tm.utils.Consts_TM;

public class MainPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static IPreferenceStore store = Activator.getDefault().getPreferenceStore();
	public StringFieldEditor server_FieldEditor;	
	public StringFieldEditor loginToken;
	public Text 			 username_Text;
	public Text 			 password_Text;
	public Text 			 ssoToken_Text;
	public Label			 password_Label;
	public Label			 ssoToken_Label;
	public Label			 or_Label;
	public Label			 result;
	public Button			 authenticate_Button;
	public Composite 	     parent;
	public AuthFieldsStatus  setAuthFieldsStatus;
	public AuthButtonStatus  setAuthButtonStatus;
	
	public class loginIntoTM_UsingPassword implements Listener
	{
		public void handleEvent(Event event) 
		{	
			result.setText("Logging in using password...");
			MainPreferences.this.parent.layout(true);
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
								TM_Preferences.setSessionId(sessionId);
								updateApplyButton();
								loginToken.load();
							}	
						}});							
				}}).start();
		}
	}
	/*public class UpdateSessionId
	{
		public UpdateSession(String sessionId)
		{
			
		}
	}*/
	public class AuthFieldsStatus implements ModifyListener
	{		
		@Override
		public void modifyText(ModifyEvent e) 
		{
			//Set enable state
			ssoToken_Text.enabled(password_Text.isEmpty());
			password_Text.enabled(ssoToken_Text.isEmpty());
			
			//Set colors
			ssoToken_Label.setForeground(colors.control());
			password_Label.setForeground(colors.control());
			or_Label      .setForeground(colors.control());
			
			if (ssoToken_Text.disabled())
			{
				ssoToken_Label.setForeground(colors.gray());
				or_Label.setForeground(colors.gray());
			}
			if (password_Text.disabled())
			{		
				password_Label.setForeground(colors.gray());
				or_Label.setForeground(colors.gray());
			}			
		}
	}
	public class AuthButtonStatus implements ModifyListener 
	{
		@Override
		public void modifyText(ModifyEvent e) 
		{
			authenticate_Button.disable(); 
		}
	}
	public class loginIntoTM_UsingSSO implements Listener
	{
		public void handleEvent(Event event) 
		{	
			result.setText("Logging in using SSO...");
			//Map<String,String> splitedQuery = splitQuery(new URL("https://teammentor-33-ci.azurewebsites.net/_customizations/sso.aspx?username=A_New_123@user.com&requestToken=07e3e666019a9148d72464498719f57e");
		}
	}
	
	public MainPreferences() 
	{
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setAuthFieldsStatus = new AuthFieldsStatus();
		setAuthButtonStatus = new AuthButtonStatus();
		
		//setDescription("\nPlease use the fields below to configure the TeamMentor behaviour,server and account\n\n");
		
		//https://teammentor-33-ci.azurewebsites.net/_customizations/sso.aspx?username=A_New_123@user.com&requestToken=07e3e666019a9148d72464498719f57e
	}
	public void addGroup_TeamMentorServer()
	{
		//parent.setLayout(new GridLayout(2, false));	
		String title = "To access password protected instances of TeamMentor you need to provide a valid Login Token.\n\n" + 
				             "Please use the fields below to set the required options:";
		
		/*new Label(parent,SWT.BORDER).setText(serverTitle);
		
		
		Group server_Config = new Group(parent, SWT.NONE);		
		server_Config.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));*/
		Group group = addGroup(title);
		
		
		server_FieldEditor = new StringFieldEditor (PreferenceInitializer.P_TEAMMENTOR_SERVER		 , "TeamMentor &Server:", group);
		addField(server_FieldEditor);
		
		
		
	}
	public void addGroup_LoginAndGetToken()
	{
		/*String groupTitle = "To get a token You can use the form below to login into the current TeamMentor server (set above)";
		new Label(parent,SWT.BORDER).setText(groupTitle);
		
		
		Group group = new Group(parent, SWT.NONE);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gridData.verticalAlignment = 20;
		group.setLayoutData(gridData);
		
		group.setLayout(new GridLayout(2, false));	*/
		
		String groupTitle = "To get a Session Token you have to provide a password or a token";
		Group group  = addGroup(groupTitle);	
			
		//Username
		new Label(group,SWT.BORDER).setText("User name:");
		
		username_Text = new Text(group,SWT.BORDER);
		username_Text.setText("");
		username_Text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		//Password
		password_Label = new Label(group,SWT.BORDER);
		password_Label.setText("Password");
		
		password_Text = new Text(group,SWT.BORDER | SWT.PASSWORD);
		password_Text.setText("");
		password_Text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
								
		or_Label = new Label(group,SWT.BORDER).text("or");;
		new Label(group,SWT.BORDER);
		
		//SSO Token
		ssoToken_Label = new Label(group,SWT.BORDER).text("SSO Token");
		ssoToken_Text = new Text(group,SWT.BORDER);
		ssoToken_Text.setText("");
		ssoToken_Text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
				
		
		new Label(group,SWT.BORDER);
//		final Label result  = new Label(group, SWT.WRAP);
		authenticate_Button = new Button(group, SWT.NONE);		
		authenticate_Button.setText("Authenticate");
		

		//Events
		password_Text.addModifyListener(setAuthFieldsStatus);
		ssoToken_Text.addModifyListener(setAuthFieldsStatus);
		authenticate_Button.addListener(SWT.Selection, new loginIntoTM_UsingPassword());
		
		/*login_Button.addListener(SWT.Selection, new Listener()		
			  	{	
					public void handleEvent(Event event) 
					{	
						result.setText("aLogging in...");
						MainPreferences.this.parent.layout(true);
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
											TM_Preferences.setSessionId(sessionId);
											updateApplyButton();
											loginToken.load();
										}	
									}});							
							}}).start();
					}
				});*/
	}
	/*public void addGroup_SSO()
	{
		//String ssoTitle = "If you have access to a SSO token (Sign-sign on), please use the form below";
		String ssoTitle =  "Option 2) login using a SSO Token (note you can paste the entire Login URL or just the SSO GUID)";
		Group sso_Group  = addGroup(ssoTitle);	
					
		new Label(sso_Group,SWT.BORDER).setText("SSO Token");
		final Text sso_Token = new Text(sso_Group,SWT.BORDER);
		sso_Token.setText("");
		sso_Token.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new Label(sso_Group,SWT.BORDER);
		Button sso_Button = new Button(sso_Group, SWT.NONE);		
		sso_Button.setText("Get Login Token (using SSO)");
		sso_Button.addListener(SWT.Selection, new loginIntoTM_UsingSSO());
		
		//new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL)
		//	.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
	}*/
	
	public void addGroupCurrentSessionId()
	{
		String title = "For reference, here is your current SessionID";
		Group group  = addGroup(title);	
		
		loginToken = new StringFieldEditor (PreferenceInitializer.P_TEAMMENTOR_SESSION_ID	 , "Session ID:"		, group);
		loginToken.setEnabled(false, group);
		addField(loginToken);
		/*new Label(group,SWT.BORDER).setText("Login Status:");
		result = new Label(group,SWT.BORDER);
		result.setText("");
		result.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,false,false,1,1)); // needed or the setText below will not show
		*/
	}
	
	public Group addGroup(String title)
	{		
		new Label(parent,SWT.BORDER).setText(title);
		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));		
		group.setLayout(new GridLayout(2, false));	
		return group;
	}
	public void createFieldEditors() 
	{	
		parent = getFieldEditorParent();

		
		addGroup_TeamMentorServer();
		addGroup_LoginAndGetToken();
	//	addGroup_SSO();
		addGroupCurrentSessionId();
//		new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL)
//			 .setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
	
		// Login form
		
		
		
		//new Label(group,SWT.BORDER);

		// SSO form
		
			
		
		// config options
			
		//new Label(parent,SWT.BORDER).setText("Misc Plugin Config Options");
		
/*		Group group_Config = new Group(parent, SWT.NONE);		
		group_Config.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_OPEN_ARTICLE_NEW_WINDOW	 , "&Open TeamMentor article in new window"	, group_Config));		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_TEAMMENTOR_LOAD_PLUGINS	 , "&Load Plugins on Startup"				, group_Config));		
		addField(new BooleanFieldEditor(PreferenceInitializer.P_TEAMMENTOR_ADVANCED_MODE , "&Show Advanced Mode Features"			, group_Config));
*/
		
		
		
		
	}

	public void logMessage(String message)
	{
		//String consoleName = "TeamMentor Scripts"; 
		Console console = Startup.eclipseApi.console;
		//console.new_MessageConsole(consoleName);
		//console.get(consoleName).clearConsole();			// use to clear
		console.write(message);

	}
	

	public void init(IWorkbench workbench) { }

	
	//URL util
	public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
	    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
	    String query = url.getQuery();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    return query_pairs;
	}
}