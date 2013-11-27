package tm.eclipse.api;

import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

public class EclipseLog 
{
	public static String MessageTitle = "TeamMentor Plugin";
	
	public static String log_Cancel(String message)
	{		
		StatusManager manager = StatusManager.getManager();
		manager.handle(new Status(Status.CANCEL, MessageTitle, message));				
		return message;
	}
	public static String log_Error(String message)
	{		
		StatusManager manager = StatusManager.getManager();
		manager.handle(new Status(Status.ERROR, MessageTitle, message));				
		return message;
	}
	public static String log_Info(String message)
	{		
		StatusManager manager = StatusManager.getManager();
		manager.handle(new Status(Status.INFO, MessageTitle, message));				
		return message;
	}
	public static String log_Ok(String message)
	{		
		StatusManager manager = StatusManager.getManager();
		manager.handle(new Status(Status.OK, MessageTitle, message));				
		return message;
	}
	public static String log_Warning(String message)
	{		
		StatusManager manager = StatusManager.getManager();
		manager.handle(new Status(Status.WARNING, MessageTitle, message));				
		return message;
	}	
}
