package tm.eclipse.swt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.*;

public class display 
{
	public static Display current() 
	{
		//SWTUtils.display() does it a bit different, but the version below will create a display when it doesn't find one
		if (Display.getCurrent() != null)
			return Display.getCurrent() ;
		if (Display.getDefault() != null)
			return Display.getDefault() ;
		return new Display ();
	}
	public static VoidResult syncExec(final VoidResult toExecute)
	{ 
		UIThreadRunnable.syncExec(current(), toExecute);		
		return toExecute;
	}
	public static Runnable syncExec(final Runnable toExecute)
	{
		UIThreadRunnable.syncExec(current(), new VoidResult() { public void run() 
							{
								toExecute.run();
							}});	
		return toExecute;
	}
	public  static <T> T syncExec(Result<T> toExecute)
	{
		return UIThreadRunnable.syncExec(current(),toExecute);
	}
	public  static <T> T[] syncExec(ArrayResult<T> toExecute)
	{
		return UIThreadRunnable.syncExec(current(),toExecute);
	}
	
	/*public  static <T> List<T> syncExec(final List<T> toExecute)
	{
		final List<T> list = new ArrayList<T>();
		syncExec(new Runnable() {
			public void run() {				
				list.add(toExecute());
			}
		}).run();
		return list;
	}*/
}
