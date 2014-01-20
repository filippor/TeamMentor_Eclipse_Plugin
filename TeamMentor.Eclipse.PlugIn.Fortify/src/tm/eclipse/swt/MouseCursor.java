package tm.eclipse.swt;

import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.Result;

public class MouseCursor 
{
	public Display display;
	public Shell   shell;
	
	public MouseCursor(Shell shell)
	{
		this.shell = shell;
		this.display= shell.getDisplay();
	}
	public Control control()
	{
		return UIThreadRunnable.syncExec(display, new Result<Control>() { public Control run()		
		{
			return MouseCursor.this.display.getCursorControl();
		}});	
	}
	public Point location()
	{
		return UIThreadRunnable.syncExec(display, new Result<Point>() { public Point run()		
		{
			return MouseCursor.this.display.getCursorLocation();
		}});	
	}
	public MouseCursor location(Point location)
	{
		return location(location.x, location.y);
	}
	public MouseCursor location(final int x, final int y)
	{
		UIThreadRunnable.syncExec(display, new VoidResult() { public void run()		
		{
			MouseCursor.this.display.setCursorLocation(x, y); 
		}});	
		return this;
	}
	
	public Cursor current()
	{
		return UIThreadRunnable.syncExec(display, new Result<Cursor>() { public Cursor run()		
		{
			return MouseCursor.this.shell.getCursor();
		}});	
	}
}
