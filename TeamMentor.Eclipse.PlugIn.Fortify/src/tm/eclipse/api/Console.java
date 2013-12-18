package tm.eclipse.api;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.framework.internal.core.ConsoleManager;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.console.*;

public class Console extends EclipseBase 
{
	public ConsolePlugin  consolePlugin;  
	public IConsoleManager consoleManager;
	
	public Console(IWorkbench workbench)
	{
		super(workbench);
		
		syncExec(new VoidResult() { public void run() 		
			{
				consolePlugin = ConsolePlugin.getDefault();
				consoleManager = consolePlugin.getConsoleManager();
			}});
		
	}
	public Console close(final String name)
	{		
		syncExec(new VoidResult() { public void run() 		
			{
				IConsole console = Console.this.get(name);
				if (console != null)
					Console.this.consoleManager.removeConsoles( new IConsole[]{ console });
			}});	
		return this;
	}
	public List<IConsole> existing()
	{
		return Arrays.asList(consoleManager.getConsoles());  
	}
	public boolean exists(String name)
	{
		return this.get(name) != null;
	}
	public MessageConsole get(String name)
	{
		for(IConsole console : existing())
			if (console.getName().equals(name))
				return (MessageConsole)console;
		return null;
	}
	public MessageConsole new_MessageConsole(String name)
	{
		if(this.get(name) == null)
		{
			MessageConsole newConsole = new MessageConsole(name, null);
			this.consoleManager.addConsoles(new IConsole[]{ newConsole });
			return newConsole;
		}
		return null;
	}
	public Console write(final String name, final String message)
	{
		syncExec(new VoidResult() { public void run() 		
			{
				MessageConsole console = Console.this.get(name);
				if (console != null)
				{
					MessageConsoleStream consoleStream = console.newMessageStream();
					consoleStream.println(message);
				}
			}});
		
		return this;
	}
}
