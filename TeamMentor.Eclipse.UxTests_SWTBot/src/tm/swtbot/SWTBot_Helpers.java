package tm.swtbot;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import tm.swtbot.helpers.*;

public class SWTBot_Helpers
{	
	public SWTWorkbenchBot	bot;
	public SWTBot_Files     files;
	public SWTBot_Views     views;
	
	public SWTBot_Helpers()
	{
		bot   = new SWTWorkbenchBot();
		files = new SWTBot_Files(bot);
		views = new SWTBot_Views(bot);		
	}
	   
	
	/*public SWTBot_Helpers addDummyTaskToWorkspace()
	{
	
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		assertNotNull(workspace);
		
		try 
		{
			workspace.run(new IWorkspaceRunnable() 
				{
					public void run(IProgressMonitor monitor) throws CoreException 
					{
						//String test = "123";
						// nothing to do!
					}
				}, new NullProgressMonitor());
		} catch (CoreException e) 
		{			// 
			e.printStackTrace();
		}
		return this;
	}*/
	

	
	

}
