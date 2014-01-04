package tm.eclipse.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

public class SyncExec implements Runnable
{
	public SyncExec()
	{
		UIThreadRunnable.syncExec(
			new VoidResult()
				{
					public void run()
					{
						this.run();
					}
				});
	}
	
	public void run() 
	{
		
	}

}
