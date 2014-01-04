package tm.eclipse.swt;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;

public class Get_Composite<T extends Composite> 
{	
	public T target;
	public Display   display;
	
	public Get_Composite(T target)
	{
		this.target  = target;
		this.display = target.getDisplay();
	}
	
	public Layout layout()
	{
		return UIThreadRunnable.syncExec(display,new Result<Layout>() { public Layout run() 
		{
			return target.getLayout();			
		}});
	}
}
