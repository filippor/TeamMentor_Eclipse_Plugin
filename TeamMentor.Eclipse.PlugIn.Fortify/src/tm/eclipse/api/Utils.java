package tm.eclipse.api;

import java.io.File;
import java.security.SecureRandom;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.utils.FileUtils;

public class Utils 
{
	public EclipseAPI 	eclipse;
	public Display    	display;
	public SecureRandom secureRandom;
	
	public Utils(EclipseAPI eclipse)
	{
		this.eclipse      = eclipse;
		this.display 	  = eclipse.display;
		this.secureRandom = new SecureRandom();
	}
	
	public String file_Contents(IPath path)
	{
		return file_Contents(path.toFile());
	}
	public String file_Contents(File file)
	{
		return FileUtils.read(file);
	}
	
	public int random_Int()
	{
		return secureRandom.nextInt();
	}
	public long random_Long()
	{
		return secureRandom.nextLong();
	}
	public String random_String()
	{
		return String.format("%d",Math.abs(random_Int()));
	}
	
}
