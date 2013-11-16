package tm.eclipse.api;

import static org.junit.Assert.*;
import org.junit.Test;

import tm.eclipse.ui.Startup;
import tm.eclipse.ui.views.Eclipse_Panel;

public class Panels_Tests 
{
	public EclipseAPI eclipseApi;
	public Panels panels;	
	
	public Panels_Tests()
	{
		eclipseApi = Startup.eclipseApi;
		panels = new Panels(eclipseApi.workbench);
		assertNotNull(eclipseApi);
		assertNotNull(panels);
		assertNotNull(panels.activePage());
		assertNotNull(panels.activeWorkbenchWindow());
		assertNotNull(panels.workbench);
	}
	
	@Test 
	public void open_Panel()
	{
		String panelId      = "New Test Panel";
		String defaultTitle = "Eclipse Panel";
		String newTitle     = "A new Title";
		Eclipse_Panel panel = panels.open_Panel(panelId);
		assertNotNull(panel);
		assertNotNull(panel.composite);
		//assertNotEquals (!defaultTitle, panel.getTitle());
		assertNotSame(defaultTitle, panel.getTitle());
		panel.title(newTitle);
		assertEquals (newTitle, panel.getTitle());		
	}
}
