package g2.groovy.api

import g2.java.api.EclipseApi.EclipseAPI
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.ui.IViewPart

class FortifyAPI 
{
	public EclipseAPI eclipseApi;
	public String 	  partTitle = "SCA Analysis Results - With TeamMentor Support";
	
	public FortifyAPI(EclipseAPI _eclipseApi)
	{
		eclipseApi = _eclipseApi;
				
		eclipseApi.partEvents.Part_Opened = 
			{	
				part ->
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
						def _eclipseApi = g2.scripts.Activator.eclipseApi;
						def _fortifyApi = g2.scripts.Activator.fortifyApi;
						
						def _currentIssue = _fortifyApi.getCurrentIssueName();
						def tmGuid =  _fortifyApi.resolveIssueNameToGuid(_currentIssue);
						_eclipseApi.log("current issue: " + _currentIssue + " : GUID : " + tmGuid);
						if (tmGuid != null)
						{
							g2.java.api.TeamMentorAPI.open_Article(tmGuid);
						}
						else
						{
							g2.java.api.TeamMentorAPI.show_No_ArticleMessage();							
						}						
					}});
				eclipseApi.log("*** Configured Tree listening event");
			}
		}
		return this;
	}

		
	
	
	public String tmMappings = """SQL Injection ,CWE ID 89 ,2abc6c1b-dd8b-41d3-ad24-c717807e43e9,SQL Injection,PHP
Cross-Site Scripting: Persistent ,CWE ID 79; CWE ID 80 ,add8a099-08da-40cc-a202-5e083267cef7,Cross-Site Scripting,PHP
Cross-Site Scripting: Poor Validation ,CWE ID 79; CWE ID 80 ,add8a099-08da-40cc-a202-5e083267cef7,Cross-Site Scripting,PHP
Cross-Site Scripting: Reflected ,CWE ID 79; CWE ID 80 ,add8a099-08da-40cc-a202-5e083267cef7,Cross-Site Scripting,PHP
Dangerous File Inclusion ,CWE ID 94; CWE ID 98 ,80380670-a6f7-431c-8709-b7fe5db60295,Remote File Inclusion,PHP
Open Redirect ,CWE ID 601 ,279ad567-2b34-44ce-8e56-8d96770ba500,Open Redirect,PHP
Path Manipulation ,CWE ID 22; CWE ID 73 ,b88255fa-86f1-4d3b-9f7f-66bc144e985c,Path Traversal,PHP
Dynamic Code Evaluation: Code Injection ,CWE ID 95 ,940d6e95-fc25-4e9d-8545-2262ed6e99ba,Code Injection,PHP
Key Management: Hardcoded Encryption Key ,CWE ID 259; CWE ID 798 ,07d18aec-f7cc-4932-a734-97e31a6f7f07,Use of Hard-coded Cryptographic Key,PHP
ASP.NET Misconfiguration: Missing Error Handling,CWE ID 12 ,fb0341e3-41c2-43bc-99d4-d12a037c1edd,Unchecked Error Condition,ASP.NET
Open Redirect ,CWE ID 601 ,fdd2fa70-27b7-4399-ba3c-3b0b1bc7360b,Open Redirect,ASP.NET
Path Manipulation ,CWE ID 22; CWE ID 73 ,ee22e5e0-0953-4276-b0e6-955becda5ee8,Path Traversal,ASP.NET
SQL Injection ,CWE ID 89 ,5fdf23f4-8ff9-4043-a3bb-2d7d10704b0e,SQL Injection,ASP.NET
SQL Injection: Castle ActiveRecord ,CWE ID 89 ,5fdf23f4-8ff9-4043-a3bb-2d7d10704b0e,SQL Injection,ASP.NET
SQL Injection: LINQ ,CWE ID 89 ,5fdf23f4-8ff9-4043-a3bb-2d7d10704b0e,SQL Injection,ASP.NET
SQL Injection: NHibernate ,CWE ID 89 ,5fdf23f4-8ff9-4043-a3bb-2d7d10704b0e,SQL Injection,ASP.NET
SQL Injection: SubSonic ,CWE ID 89 ,5fdf23f4-8ff9-4043-a3bb-2d7d10704b0e,SQL Injection,ASP.NET
LDAP Injection ,CWE ID 90 ,26cf3a5c-3bbd-4239-b74f-f776df950ac8,LDAP Injection,ASP.NET
Log Forging ,CWE ID 117 ,700cbc96-fcfe-4ec7-b4cd-fc3eb8a98e40,Log Forging,ASP.NET
Cross-Site Scripting: Persistent ,CWE ID 79; CWE ID 80 ,937f2173-5e39-48b6-bb3a-ecfd8f052bb0,Cross-Site Scripting,ASP.NET
Cross-Site Scripting: Poor Validation ,CWE ID 79; CWE ID 80 ,937f2173-5e39-48b6-bb3a-ecfd8f052bb0,Cross-Site Scripting,ASP.NET
Cross-Site Scripting: Reflected ,CWE ID 79; CWE ID 80 ,937f2173-5e39-48b6-bb3a-ecfd8f052bb0,Cross-Site Scripting,ASP.NET
XPath Injection ,CWE ID 643,9da2fccb-d20c-4e74-9f59-349d799e25cf,XPath Injection,ASP.NET
Password Management: Hardcoded Password,CWE ID 259; CWE ID 798 ,0cc358fe-0396-4142-bab5-ad327920b340,Use of Hard-coded Password,ASP.NET
Password Management: Password in Comment ,CWE ID 615,0cc358fe-0396-4142-bab5-ad327920b340,Use of Hard-coded Password,ASP.NET
Password Management ,CWE ID 256 ,9a44e0a4-823a-4d99-90fb-7de648a094c8,Insufficiently Protected Credentials,ASP.NET
Password Management: Weak Cryptography ,CWE ID 261 ,9a44e0a4-823a-4d99-90fb-7de648a094c8,Insufficiently Protected Credentials,ASP.NET
Command Injection ,CWE ID 77; CWE ID 78 ,ea00a8ee-1248-42b5-9954-439a93fb14d8,Command Injection,ASP.NET
Missing XML Validation ,CWE ID 112 ,099d9a5b-3ac2-4acb-9192-5b6d7fafda34,Missing XML Validation,ASP.NET
Missing XML Validation(validating_reader) ,CWE ID 112 ,099d9a5b-3ac2-4acb-9192-5b6d7fafda34,Missing XML Validation,ASP.NET
Cross-Site Request Forgery ,CWE ID 352 ,36d0c6a6-8a5c-4586-b0d5-917a9aff9557,Cross-Site Request Forgery,ASP.NET
Password Management: Password in Configuration File ,CWE ID 13; CWE ID 260; CWE ID 555 ,0cc358fe-0396-4142-bab5-ad327920b340,Use of Hard-coded Password,ASP.NET
Command Injection ,CWE ID 77; CWE ID 78 ,2e03d087-3614-4927-8d20-d9efc3f7bbc4,Command Injection,Java
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
