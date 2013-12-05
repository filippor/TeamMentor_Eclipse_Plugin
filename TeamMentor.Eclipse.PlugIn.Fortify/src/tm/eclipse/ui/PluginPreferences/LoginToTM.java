package tm.eclipse.ui.PluginPreferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.ui.Startup;

public class LoginToTM extends PreferencePage implements
		IWorkbenchPreferencePage {

	public LoginToTM() {
		// TODO Auto-generated constructor stub
	}

	public LoginToTM(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public LoginToTM(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Control createContents(Composite parent) 
	{
		Label label1=new Label(parent, SWT.NULL);
		  label1.setText("User Name: ");
		  
		  final Text username = new Text(parent, SWT.SINGLE | SWT.BORDER);
		  username.setText("");
		  //username.setTextLimit(30);
		  
		  Label label2=new Label(parent, SWT.NULL);
		  label2.setText("Password: ");
		  
		  final Text password = new Text(parent, SWT.SINGLE | SWT.BORDER);
		  System.out.println(password.getEchoChar());
		  password.setEchoChar('*');
		  //password.setTextLimit(30);

		  Button button=new Button(parent,SWT.PUSH);
		  button.setText("Submit");
		  
		  final Label result=new Label(parent, SWT.NULL);
		  result.setText("                                              ");
		  
		   
		  button.addListener(SWT.Selection, new Listener() 
		  	{	
			  Shell shell = Startup.eclipseApi.shell;
			  public void handleEvent(Event event) 
			  {				  
				  result.setText("Logging in...");
				  String sessionId = TeamMentorAPI.loginIntoTeamMentor(username.getText(), password.getText());
				  if (sessionId == null || sessionId.equals("") || sessionId.equals("00000000-0000-0000-0000-000000000000"))
					 result.setText("Login Failed, please try again");
				  else
				  {
					  MainPreferences.setSessionId(sessionId);
					  result.setText("Login OK (sessionId updated)");
					  performApply();
					  performOk();					  
				  }
				  result.redraw();
				
				  }
				  });
		  username.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		  password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		  		  
		return null;
	}
	@Override
	public void createControl(Composite parent)
	{
		super.createControl(parent);
		Button applyButton = getApplyButton();
		applyButton.setVisible(false);
		getDefaultsButton().setVisible(false);
	}
	public boolean performOk()
	{
		return true;
	}
	protected void performDefaults() 
	{
		
	}

}
