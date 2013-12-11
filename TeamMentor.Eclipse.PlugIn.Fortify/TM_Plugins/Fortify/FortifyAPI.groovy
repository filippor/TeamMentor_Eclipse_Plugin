package tm.eclipse.groovy

import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Tree
import org.eclipse.ui.IViewPart
import org.eclipse.ui.IWorkbenchPartReference

import tm.eclipse.api.EclipseAPI;

class FortifyAPI
{
	public static FortifyAPI current;
	public EclipseAPI eclipseApi;
	public String 	  partTitle = "SCA Analysis Results - With TeamMentor Support";
	
	
	public FortifyAPI()
	{
		current = this;
		eclipseApi =  tm.eclipse.ui.Startup.eclipseApi;
		eclipseApi.log("*** Configuring FortifyAPI support");

		add_ContentProviderStateListener();

/*		eclipseApi.partEvents.Part_Opened =
			{
				IWorkbenchPartReference part ->
						eclipseApi.log("View Opened: " + part.id);
						this.setFortifyHooks();
			};*/
	}
	public FortifyAPI add_ContentProviderStateListener()
    { 
	    eclipseApi.log("   - addContentProviderStateListener");
		def issuesList     		    = eclipseApi.activeWorkbenchPage.findView("com.fortify.awb.views.views.IssuesListView");  // gets a reference to the Fortify view
		def fortifyClassLoader  = issuesList.class.getClassLoader();																		 // gets the class loader of the Fortify plugin
		def currentThread = Thread.currentThread();																						   // get the curren thead
		def originalClassLoader = currentThread.getContextClassLoader();														   // save its class loader
		currentThread.setContextClassLoader(fortifyClassLoader);	
	   
		removeExisting_ContentProviderStateListener()

		def interpreter = new bsh.Interpreter();
        interpreter.set("fortifyApi", this); 
		def stateListener = interpreter.eval(beanShell_ContentProviderStateListener);	

		def beanShellScript =  "return com.fortify.awb.util.SWTIntegrationUtil.class";
		def swtIntegrationUtil_Class  =  new bsh.Interpreter().eval(beanShellScript);			
		def swtIntegration = swtIntegrationUtil_Class.newInstance();
		swtIntegration.registerContentProviderStateListener(stateListener);
		eclipseApi.log("   - new stateListener: " + stateListener.toString());

		currentThread.setContextClassLoader(originalClassLoader);																	  // restore original class loader

		return this;
    }

	public FortifyAPI removeExisting_ContentProviderStateListener()
	{
		def beanShellScript =  "return com.fortify.awb.util.SWTIntegrationUtil.class";
		def swtIntegrationUtil_Class  =  new bsh.Interpreter().eval(beanShellScript);			
		def swtIntegration = swtIntegrationUtil_Class.newInstance();
		swtIntegration.cpStateListeners.toList().collect
		{
			if (it.toString().contains("Bsh object"))
			{
				swtIntegration.removeContentProviderStateListener(it)
				 eclipseApi.log("   - removing ContentProviderStateListener: " + it.toString());
			}
			//		swtIntegration.issueListeners.remove(it)
//			 eclipseApi.log("   - ContentProviderStateListener: " + it.toString());
		}
		return this;
	}

	public void showIssue(Object issue, String category, String recommendation)
    {

		if (recommendation != null && recommendation != "")
		{
			def tmGuid =  resolveIssueNameToGuid(category);
			eclipseApi.log("issue category: " + category + " : GUID : " + tmGuid);
			if (tmGuid != null)
			{
				tm.eclipse.api.TeamMentorAPI.open_Article(tmGuid);
			}
			else
			{
				def html = "<h4>Fortify Recommentation</h4>" + 
				                  "</br>Since there is no article for the current Fortify mapping, here is the default Fortify Recommendation content:<br><br>" + 
		    			              "<pre>" + recommendation + "</pre>"
				tm.eclipse.api.TeamMentorAPI.show_Html_With_TeamMentor_Banner(html);
			}
		}
		else
			showNoIssueMessage();
		
    }

	public void showNoIssueMessage()
	{
		eclipseApi.log("showNoIssueMessage");
		tm.eclipse.api.TeamMentorAPI.show_Html_With_TeamMentor_Banner("No Fortify issue selected.");
	}

	public IViewPart getIssuesListView()
	{
		def activePage =eclipseApi.activeWorkbenchPage;
		if (activePage!=null)
			return activePage.findView("com.fortify.awb.views.views.IssuesListView");
		return null;
	}
	
	public String getCurrentIssueName()
	{
		def issuesList = getIssuesListView();
		if (issuesList == null)
			return null;
		if (issuesList.tree.selection == null || issuesList.tree.selection.firstElement == null)
		{
			eclipseApi.log("in getCurrentIssueName, got issuesList, couldn't get issuesList.tree.selection.firstElement");
			return null;
		}
		//def data = issuesList.focusItem.getData();  // focus item is not working
		
		def data = issuesList.tree.selection.firstElement;
		if (data == null)
			return null;
		if (data.class.name == "com.fortify.ui.model.issue.IssueGroup")
			return data.name;
		if (data.parent.class.name == "com.fortify.ui.model.issue.IssueGroup")
			return data.parent.name;
		if (data.parent.parent.class.name == "com.fortify.ui.model.issue.IssueGroup")
			return data.parent.parent.name;
		return null;
	}
	
	public String resolveIssueNameToGuid(String issueName)
	{
		if (issueName!= null)
		{
			def lines = tmMappings.split("\n");
	
			for(line in lines)
			{
				if (line.startsWith(issueName))
					return line.split(",")[2];
			}
		}
		return null;
	}
	
/*	public FortifyAPI setFortifyHooks()
	{
		def issuesList = getIssuesListView();
		if (issuesList!=null)
		{
			if (issuesList.getPartName() != partTitle)
			{
				eclipseApi.log("*** APPLYING TEAMMENTOR FORTIFY EVENT LISTENERS");//changing Fortify IssuesListView title");
				eclipseApi.log("Title before: " + issuesList.getPartName());
				issuesList.setPartName(partTitle);
				eclipseApi.log("Title after: " + issuesList.getPartName());
				
				Tree tree = issuesList.tree.getTree();
				
				tree.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e)
					{
					  try
					  {
						def _eclipseApi = tm.eclipse.ui.Startup.eclipseApi;
						def _fortifyApi = FortifyAPI.current;
						_eclipseApi.log("Issue was selected on Fortify View");
//						_eclipseApi.log("current issue: " + _currentIssue )
//						return;

						def _currentIssue = _fortifyApi.getCurrentIssueName();

						def tmGuid =  _fortifyApi.resolveIssueNameToGuid(_currentIssue);
						_eclipseApi.log("current issue: " + _currentIssue + " : GUID : " + tmGuid);
						if (tmGuid != null)
						{
							tm.eclipse.api.TeamMentorAPI.open_Article(tmGuid);
						}
						else
						{
							def recommentdations = _eclipseApi.activeWorkbenchPage.findView("com.fortify.awb.views.views.RecommendationsView");
							def markupReader = recommentdations.site.model.widget.children[0].children[0].children[0].children[0];

							//tm.eclipse.api.TeamMentorAPI.show_No_ArticleMessage();
						    def txt = markupReader.getText();
							def html = "<h4>Fortify Recommentation</h4>" + 
				             "</br>Since there is no article for the current Fortify mapping, here is the default Fortify Recommendation content:<br><br>" + 
		    			         "<pre>" + txt + "</pre>"

						TeamMentorAPI.show_Html_With_TeamMentor_Banner(html);

						}
					  }
					  catch(Exception ex)
					  {
						tm.eclipse.ui.Startup.eclipseApi.log("ERROR in tree.addSelectionListener: " + ex.getMessage());
					  }
					}});
				eclipseApi.log("*** Configured Tree listening event");
			}
		}
		return this;
	}
*/


	public String beanShell_ContentProviderStateListener = """ 

		import com.fortify.awb.util.SWTIntegrationUtil;
		import com.fortify.ui.model.contentProvider.listeners.*;
		import com.fortify.ui.model.issue.*;
		import com.fortify.ui.model.contentProvider.*;
		import com.fortify.ui.model.util.render.SCAPrettyPrinter;


     	return new ContentProviderStateListener() 
	 	   {
			public abstract void notifyEvent(ContentProvider contentProvider,
												 			 ContentProviderEventType eventType)
			{		
				    currentThread =  Thread.currentThread();
					originalClassLoader = currentThread.getContextClassLoader();	
				    fortifyClassLoader  = 	contentProvider.getClass().getClassLoader();
					currentThread.setContextClassLoader(fortifyClassLoader);
					
 
					tm.eclipse.ui.Startup.eclipseApi.log(" **** ContentProviderStateListener notify event: " + eventType);

				   if (eventType == ContentProviderEventType.SELECTED_ISSUES_CHANGED)
					{
//						fortifyApi.eclipseApi.log("HHEEEERE!!!!!");
						tm.eclipse.ui.Startup.eclipseApi.log("    - contentProvider : "     + contentProvider.toString());
						issue = contentProvider.getSelectedIssue();
						tm.eclipse.ui.Startup.eclipseApi.log("    - issue : "     + issue.toString());
						
						descriptableIssue = issue.getDescriptableIssue();				
					    if(descriptableIssue == null)
						{
							tm.eclipse.ui.Startup.eclipseApi.log("Error: descriptableIssue was null");
							fortifyApi.showIssue(null, "","");
					    }
						else
						{
							category = IssueUtil.getCategoryString(descriptableIssue);
							recommendation = SCAPrettyPrinter.getRecommendation(descriptableIssue, false);

							fortifyApi.showIssue(issue, category, recommendation);
						}
/*						if (recommendation != null && recommendation != "")
						{


//							recommendation = "</br>Since there is no TeamMentor article for the current Fortify mapping, here is the default Fortify Recommendation content:<br><br>" +
//														   "<pre>" + recommendation + " </pre>";
//						    tm.eclipse.api.TeamMentorAPI.show_Html_With_TeamMentor_Banner(recommendation);	
						}
						else
							fortifyApi.showNoIssueMessage();*/
					}
 					currentThread.setContextClassLoader(originalClassLoader);
			}
          } 
""";	
	
	
	
	public String tmMappings = """Command Injection ,CWE ID 77; CWE ID 78 ,2e03d087-3614-4927-8d20-d9efc3f7bbc4,Command Injection,Java
Cross-Site Scripting: DOM ,CWE ID 79; CWE ID 80 ,e1066fc2-22e3-47b3-ac0d-34a6fa70da68,Cross-Site Scripting,Java
Cross-Site Scripting: External Links ,CWE ID 79; CWE ID 80 ,e1066fc2-22e3-47b3-ac0d-34a6fa70da68,Cross-Site Scripting,Java
Cross-Site Scripting: Persistent ,CWE ID 79; CWE ID 80 ,e1066fc2-22e3-47b3-ac0d-34a6fa70da68,Cross-Site Scripting,Java
Cross-Site Scripting: Poor Validation ,CWE ID 79; CWE ID 80 ,e1066fc2-22e3-47b3-ac0d-34a6fa70da68,Cross-Site Scripting,Java
Cross-Site Scripting: Reflected ,CWE ID 79; CWE ID 80 ,e1066fc2-22e3-47b3-ac0d-34a6fa70da68,Cross-Site Scripting,Java
LDAP Injection ,CWE ID 90 ,418cfb77-5d0a-4fd9-9825-1732cf40070d,LDAP Injection,Java
Log Forging ,CWE ID 117 ,59f42764-e58b-4695-962f-5efdf1f36bd6,Log Forging,Java
Open Redirect ,CWE ID 601 ,79aaa6c6-54bd-4a98-9649-77f81312085e,Open Redirect,Java
SQL Injection ,CWE ID 89 ,c92edd0d-f59a-4dd5-bed3-48a2190c895f,SQL Injection,Java
SQL Injection: Hibernate ,CWE ID 89 ,c92edd0d-f59a-4dd5-bed3-48a2190c895f,SQL Injection,Java
SQL Injection: JDO ,CWE ID 89 ,c92edd0d-f59a-4dd5-bed3-48a2190c895f,SQL Injection,Java
SQL Injection: Persistence ,CWE ID 89 ,c92edd0d-f59a-4dd5-bed3-48a2190c895f,SQL Injection,Java
SQL Injection: iBatis Data Map ,CWE ID 89 ,c92edd0d-f59a-4dd5-bed3-48a2190c895f,SQL Injection,Java
Missing XML Validation ,CWE ID 112 ,fa554526-0e27-470f-ba6c-464c2250cfbd,Missing XML Validation,Java
Password Management,CWE ID 256 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Empty Password ,CWE ID 259 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Hardcoded Password ,CWE ID 259; CWE ID 798 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Null Password ,CWE ID 259 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Password in Comment ,CWE ID 615,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Password in Redirect ,CWE ID 359 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Weak Cryptography ,CWE ID 261 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Empty Password in Configuration File,CWE ID 258 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java
Password Management: Password in Configuration File ,CWE ID 13; CWE ID 260; CWE ID 555 ,ac9e0d29-dfd0-4671-98f5-7106e6505c24,Insufficiently Protected Credentials,Java""";
}

return new FortifyAPI();