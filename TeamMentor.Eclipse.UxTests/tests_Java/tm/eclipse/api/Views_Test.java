package tm.eclipse.api;

import static org.junit.Assert.*;
import static tm.tests.helpers.ExtraAsserts.*;

import java.util.List;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.junit.Test;

import tm.eclipse.ui.Startup;
import tm.tests.helpers.Eclipse_Consts;

public class Views_Test 
{
	public EclipseAPI eclipseApi;
	public Views views;	
	
	public Views_Test()
	{
		eclipseApi = Startup.eclipseApi;
		views = new Views(eclipseApi.workbench);
		assertNotNull(eclipseApi);
		assertNotNull(views);
		assertNotNull(views.activePage());
		assertNotNull(views.activeWorkbenchWindow());
		assertNotNull(views.workbench);
	}
	
	@Test 
	public void get_Views_References()
	{
		List<IViewReference> viewReferences = views.get_Views_References();
		assertNotNull(viewReferences);
		assertTrue(viewReferences.size() >0);
		System.out.println("***** get_Views_References *****");
		for(IViewReference viewReference : viewReferences)
			System.out.println(String.format("id: %s  /t/t partName: %s", 
											  viewReference.getId(),											  
											  viewReference.getPartName()));
		System.out.println("********************************");
	}	
	
	@SuppressWarnings("restriction")
	@Test 
	public void open_View()
	{		
		IViewPart viewPart = views.open_View(Eclipse_Consts.VIEW_ID_WELCOME_SCREEN);			
		assertNotNull(viewPart);				
		if (!(viewPart instanceof org.eclipse.ui.internal.ErrorViewPart))		// we get ErrorViewPart when running these tests as an RCP app
			assertIsClass(viewPart, org.eclipse.ui.internal.ViewIntroAdapterPart.class);
		
		IViewPart noView = views.open_View("AAAAAAA");
		assertNull(noView);		
			
	}
}
