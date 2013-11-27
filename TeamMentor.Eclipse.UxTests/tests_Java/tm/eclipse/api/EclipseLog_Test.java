package tm.eclipse.api;

import static org.junit.Assert.*;
import static tm.eclipse.api.EclipseLog.*;

import org.eclipse.core.internal.runtime.*;
import org.eclipse.core.runtime.*;
import org.eclipse.ui.statushandlers.*;
import org.junit.*;


public class EclipseLog_Test 
{
	public LogListener logListener;
	public StatusManager statusManager;
	
	public class LogListener implements ILogListener 
	{		
		public IStatus lastLogEntry;	
		public void logging(IStatus status, String plugin) 
		{
			lastLogEntry = status;
		}
	}
	
	@SuppressWarnings("restriction")
	@Before
	public void beforeClass()
	{
		logListener = new LogListener();
		statusManager = StatusManager.getManager();
		Platform.addLogListener(logListener);
		
		assertNotNull(logListener);
		assertNotNull(statusManager);
		assertNull   (logListener.lastLogEntry);
		assertTrue   (RuntimeLog.contains(logListener));
	}
	
	@SuppressWarnings("restriction")
	@After
	public void afterClass()
	{
		Platform.removeLogListener(logListener);
		assertFalse(RuntimeLog.contains(logListener));
	}	
	
	public void check_LastLogEntry(int expectedSeverity, String expectedMessage)
	{
		IStatus logEntry = logListener.lastLogEntry;
		assertEquals(expectedSeverity, logEntry.getSeverity());
		assertEquals(expectedMessage, logEntry.getMessage());
	}
	
	@Test
	public void test_log_Methods()
	{
		log_Cancel ("cancel Message");	   
		check_LastLogEntry(Status.CANCEL , "cancel Message" );
		
		log_Error  ("error Message");
		check_LastLogEntry(Status.ERROR  , "error Message"  );
				
		log_Info   ("info Message");
		check_LastLogEntry(Status.INFO   , "info Message"   );
		
		log_Ok     ("ok Message");
		check_LastLogEntry(Status.OK     , "ok Message"     );
		
		log_Warning("warning Message");
		check_LastLogEntry(Status.WARNING, "warning Message");
	}
	
}
