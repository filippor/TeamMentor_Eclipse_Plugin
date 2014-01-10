package tm.eclipse.swt;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;




//import tm.eclipse.swt.controls.Composite;
//import tm.eclipse.swt.controls.Text;
import tm.lang.Reflection;

public class  Control_Set<T extends Control>  
{	
	public T 				target;
	public Display   		display;
	public Reflection 		reflection;
	
	public Control_Color <T> color;
	public Control_Event <T> event;
	public Control_Layout<T> layout;
	
	public Control_Set(T target)
	{
		this.target  	= target;
		this.display 	= target.getDisplay();
		this.reflection = new Reflection(this);		
		this.color 	    = new Control_Color<T>(target);
		this.event 	    = new Control_Event<T>(target);
		this.layout 	= new Control_Layout<T>(target);
	}	
	public T location(final int x, final int y)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			target.setLocation(x, y);			
		}});	
		return target;
	}

	public T size(final int x, final int y)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			target.setSize(x, y);			
		}});	
		return target;
	}
	public T toolTip(final String toolTip)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			target.setToolTipText(toolTip);			
		}});
		return target;
	}
}
