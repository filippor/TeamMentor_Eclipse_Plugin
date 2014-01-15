package tm.eclipse.api;

import java.io.IOException;
import org.eclipse.swt.widgets.Display;

public class Platform
{
	public EclipseAPI eclipse;
	public Display    display;
	
	public Platform(EclipseAPI eclipse)
	{
		this.eclipse = eclipse;
		this.display = eclipse.display;
	}
	
	@SuppressWarnings("restriction")
	public org.eclipse.core.internal.runtime.InternalPlatform internalPlatform()
	{
		return org.eclipse.core.internal.runtime.InternalPlatform.getDefault();
	}
	public Process exec(String processPath, String arguments)
	{
		try 
		{
			//return Runtime.getRuntime().exec(processPath, arguments);  //didn't work
			return new ProcessBuilder(processPath, arguments).start();
		} catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
