package tm.eclipse.swt.controls;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

import tm.eclipse.swt.Add_Composite;
import tm.eclipse.swt.Get_Composite;
import tm.eclipse.swt.Set_Composite;

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
	public Add_Composite<Shell> add;
	public Get_Composite<Shell> get;
	public Set_Composite<Shell> set;
	public Display display;
	
	public Shell(Display display) 
	{
		 super(display);
		 this.display = display;
		 add = new Add_Composite<Shell>(this);
		 get = new Get_Composite<Shell>(this);
		 set = new Set_Composite<Shell>(this);
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
}
