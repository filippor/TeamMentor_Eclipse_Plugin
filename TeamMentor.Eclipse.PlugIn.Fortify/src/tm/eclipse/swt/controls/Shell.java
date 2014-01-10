package tm.eclipse.swt.controls;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

import tm.eclipse.swt.controls.Composite;
import tm.eclipse.swt.Composite_Add;
import tm.eclipse.swt.Control_Get;
import tm.eclipse.swt.Control_Set;
import tm.lang.Reflection;

public class Shell extends org.eclipse.swt.widgets.Shell 
{
	public static Shell onDefaultDisplay()
	{
		final Display display = Display.getDefault();
		return UIThreadRunnable.syncExec(display,new Result<Shell>() { public Shell run()
				{
					return new Shell(display);
				}});

	}
	public Composite_Add<Composite> add;
	public Control_Get<Composite> get;
	public Control_Set<Composite> set;
	public Display display;
	public Reflection reflection;
	public Composite composite;
	
	public Shell(Display display) 
	{
		 super(display);
		 this.display 	 = display;
		 this.reflection = new Reflection(this);
		 this.composite = new Composite((org.eclipse.swt.widgets.Composite)this); // need to wrap the current shell's composite into our own Composite 
		 add = new Composite_Add<Composite>(composite);
		 get = new Control_Get<Composite>(composite);
		 set = new Control_Set<Composite>(composite);
	}
	
	protected void checkSubclass()
	{}
	
	@Override 
	public void close()
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			Shell.super.close();

		}});
	}

	public Shell sleep(int miliseconds)
	{
		try 
		{
			Thread.sleep(miliseconds);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return this;
	}
	public Shell show()
	{		
		return this;
	}	
	
	@Override 
	public void open()
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			Shell.super.open();			
		}});
	}
	@Override 
	public void pack()
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			Shell.super.pack();			
		}});
	}
	public Shell packAndOpen()
	{
		this.pack();
		this.open();
		return this;
	}
	public Shell title(final String title)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			Shell.this.setText(title);			
		}});		
		return this;
	}
	public Shell move(final int x, final int y)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			Shell.super.setLocation(x,y);			
		}});
		return this;
	}
	public Shell size(final int x, final int y)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			Shell.super.setSize(x,y);			
		}});
		return this;
	}
}