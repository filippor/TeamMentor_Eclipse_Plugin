package tm.eclipse.swt.controls;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;

public class Control_Ex
{
	public Control control;
	public Control_Ex(Control _control)
	{
		control = _control;
	}
	public Composite parent()
	{
		return UIThreadRunnable.syncExec(control.getDisplay(),new Result<Composite>() { public Composite run() 
			{
				return control.getParent().getParent();
			}});
	}
}
