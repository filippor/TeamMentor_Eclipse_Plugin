package tm.eclipse.api;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import org.eclipse.swtbot.swt.finder.results.*;

import static org.junit.Assert.*;
import static tm.utils.ExtraAsserts.*;

import java.util.List;





/*import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;*/
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
//import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.junit.Test;

import tm.eclipse.ui.Startup;
import tm.eclipse.ui.views.SimpleEditor;
import tm.utils.Consts_Eclipse;

public class Views_Test 
{
	public EclipseAPI eclipseApi;
	public Views views;	
	
	public Views_Test()
	{
		eclipseApi = Startup.eclipseApi;
		views = new Views(eclipseApi.workbench);
		assertNotNull(eclipseApi);
		syncExec(new VoidResult() { @Override public void run() 		
			{ 
				assertNotNull(views);
				assertNotNull(views.activePage);
				assertNotNull(views.workbenchWindow);
				assertNotNull(views.workbench);	
			}});
	}
	
	@Test 
	public void get_Views_References()
	{		
		syncExec(new VoidResult() { public void run() 		
			{ 
				List<IViewReference> viewReferences = views.get_Views_References();		
				assertNotNull(viewReferences);
				if(viewReferences.size() >0)
				{
					assertTrue(viewReferences.size() >0);
					System.out.println("***** get_Views_References *****");
					for(IViewReference viewReference : viewReferences)
						System.out.println(String.format("id: %s  /t/t partName: %s", 
														  viewReference.getId(),											  
														  viewReference.getPartName()));
					System.out.println("********************************");
				}
			}});
	}	
	
	@Test 
	public void get_Views_Reference()
	{
		syncExec(new VoidResult() { @Override public void run() 		
			{ 
				List<IViewReference> viewReferences = views.get_Views_References();
				assertNotNull(viewReferences);
				if (viewReferences.size() >0)
				{
					for(IViewReference viewReference : viewReferences)
					{
						System.out.println("id:  " + viewReference.getId());
						IViewReference getViewReference = views.get_View_Reference(viewReference.getId());
						assertNotNull(getViewReference);
					}
				}
				else
				{
					IViewPart viewPart = views.open_View(SimpleEditor.ID);
					assertNotNull(viewPart);
					viewReferences = views.get_Views_References();
					assertEquals(1, viewReferences.size());
					assertEquals(viewReferences.get(0).getId(), SimpleEditor.ID);
					
					/* the ones below are now working because the UI is not refreshing
					 * the idea was to remove the view added above 
					 */		
					//viewPart.dispose
					//viewReferences = views.get_Views_References();
					//assertEquals(0, viewReferences.size());
				}
			}});
	}

	@Test 
	public void open_View() throws InterruptedException
	{	
		//eclipseApi.invokeOnThread(new Runnable() { @Override public void run() 		
		//	{
		
		//open an close a view from this plugin
		IViewPart viewPart = views.open_View(SimpleEditor.ID);
		assertNotNull(viewPart);				
		
		assertIsClass(viewPart, SimpleEditor.class);
		assertNotNull(views.get_View_Reference(SimpleEditor.ID));
		Startup.eclipseApi.views.close(viewPart);
		assertNull(views.get_View_Reference(SimpleEditor.ID));
		//				
			
		//open a non existing view
		IViewPart noView = views.open_View("AAAAAAA");
		assertNull(noView);
		//	}});
	}

	@Test
	public void closeAboutWindow()
	{
		EclipseAPI eclipseAPI = Startup.eclipseApi;
		IViewReference viewReference = eclipseAPI.views.get_View_Reference(Consts_Eclipse.VIEW_ID_WELCOME_SCREEN);
		if (viewReference != null)
		{
			eclipseAPI.views.close(viewReference);
			viewReference = eclipseAPI.views.get_View_Reference(Consts_Eclipse.VIEW_ID_WELCOME_SCREEN);
			assertNull(viewReference);
		}
	}
}
