	package tm.eclipse.groovy

import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.ui.IViewPart
import org.eclipse.ui.IWorkbenchPartReference

import tm.eclipse.api.EclipseAPI;

class FortifyAPI 
{
	public EclipseAPI eclipseApi;
	public String 	  partTitle = "SCA Analysis Results - With TeamMentor Support";
	
	public FortifyAPI(EclipseAPI _eclipseApi)
	{
		eclipseApi = _eclipseApi;
				
		eclipseApi.partEvents.Part_Opened = 
			{	
				IWorkbenchPartReference part ->
						eclipseApi.log("View Opened: " + part.id);
						this.setFortifyHooks();
			};		
	}
	public IViewPart getIssuesListView()
	{
		def activePage =eclipseApi.activePage();
		if (activePage!=null) 
			return activePage.findView("com.fortify.awb.views.views.IssuesListView");
		return null;		
	}
	
	public String getCurrentIssueName()
	{
		def issuesList = getIssuesListView();
		if (issuesList == null)
		return null;
		
		def data = issuesList.focusItem.getData();
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
	
	public FortifyAPI setFortifyHooks()
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
				
				def tree = issuesList.tree.getTree();
				
				tree.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e)
					{
						def _eclipseApi = tm.eclipse.ui.Activator.eclipseApi;
						def _fortifyApi = tm.eclipse.ui.Activator.fortifyApi;
						
						def _currentIssue = _fortifyApi.getCurrentIssueName();
						def tmGuid =  _fortifyApi.resolveIssueNameToGuid(_currentIssue);
						_eclipseApi.log("current issue: " + _currentIssue + " : GUID : " + tmGuid);
						if (tmGuid != null)
						{
							tm.eclipse.api.TeamMentorAPI.open_Article(tmGuid);
						}
						else
						{
							tm.eclipse.api.TeamMentorAPI.show_No_ArticleMessage();							
						}						
					}});
				eclipseApi.log("*** Configured Tree listening event");
			}
		}
		return this;
	}

		
	
	
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
