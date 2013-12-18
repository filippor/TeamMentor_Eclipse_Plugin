package tm.eclipse.api;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.junit.Assert.*;

import java.util.List;

import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.junit.Test;

import tm.eclipse.ui.Startup;

public class Console_Test 
{
	public EclipseAPI eclipseApi;
	public Console console;	
	
	String consoleName = "Message Console for JUnit Test";  // there shouldn't be one with this name
	
	public Console_Test()
	{
		eclipseApi = Startup.eclipseApi;
		
		console = new Console(eclipseApi.workbench);
		assertNotNull(eclipseApi);

		syncExec(new VoidResult() { @Override public void run() 		
			{ 
				assertNotNull(console);				
				assertNotNull(console.activePage);
				assertNotNull(console.workbenchWindow);
				assertNotNull(console.workbench);				
				assertNotNull(console.consolePlugin);
				assertNotNull(console.consoleManager);  // will fail if accessed outside the UI thread	
				
			}});	
//		console.close(consoleName);  // while development of this test
	}
	@Test
	public void get()
	{
		assertNull(console.get("AAAAAAAA"));
	}
	@Test 
	public void existing()
	{			
		List<IConsole> consoles = console.existing();
		assertNotNull("consoles was null"  , consoles);
		assertTrue   ("Console size was 0", consoles.size() > 0);		
	}
	@Test 
	public void new_MessageConsole()
	{
		
		assertFalse(console.exists(consoleName));
		MessageConsole newConsole = console.new_MessageConsole(consoleName);
		assertNotNull(newConsole);		
		console.close(consoleName);		
		assertFalse(console.exists(consoleName));
	}
	
	@Test
	public void write()
	{
		assertFalse(console.exists(consoleName));
		console.new_MessageConsole(consoleName);
		assertTrue (console.exists(consoleName));
		console.write(consoleName, "This is a message");		
		console.close(consoleName);
	}
	/*		syncExec(new VoidResult() { public void run() 		
	{
	}});
	*/
}
