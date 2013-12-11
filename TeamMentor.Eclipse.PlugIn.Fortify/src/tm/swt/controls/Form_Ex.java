package tm.swt.controls;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.ArrayResult;

public class Form_Ex 
{
	public Display display;
	public Shell   shell;
	public Thread  thread_toClose;
	
	public Form_Ex()
	{
		display = tm.swt.display.current();
		display.syncExec(new Runnable() { public void run() 
					{
						shell = new Shell(display);		
					}});
		
	}
	
	public Form_Ex show()
	{
		display.syncExec(new Runnable() { public void run() 
					{
						shell.open();
					}});
		return this;
	}
	public Form_Ex layout_Fill()
	{
		display.syncExec(new Runnable() { public void run() 
					{
						shell.setLayout(new FillLayout());
					}});
		return this;
	}
	
	
	public Form_Ex waitForClose()
	{
		display.syncExec(new Runnable() { public void run() 
					{
						while (!shell.isDisposed()) 
						{
							if (!display.readAndDispatch())
								display.sleep();
						}
					}});
		return this;
	}
	
	public Form_Ex close()
	{
		display.syncExec(new Runnable() { public void run() 
					{
						shell.dispose();
					}});
		return this;
	}
		
	public Form_Ex close_InNSeconds(final int seconds)
	{
		return close_InMiliSeconds(seconds * 1000);
	}
	public Form_Ex close_InMiliSeconds(final int miliSeconds)
	{
		thread_toClose = new Thread(new Runnable() { public void run()
		{		
			try 
			{
				Thread.sleep(miliSeconds);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			display.syncExec(new Runnable() { public void run() 
						{
							shell.dispose();
						}});			
			}
		});
		thread_toClose.start();		
		return this;
	}
	
	public List<Control> controls()
	{
		
		Control[] controls = UIThreadRunnable.syncExec(display, new ArrayResult<Control>() { public Control[] run()	
								{ 
									return shell.getChildren();
								}});
		return Arrays.asList(controls);
	}
	
	public <T> T control(Class<T> clazz)
	{
		for(Control control : controls())
			if (control.getClass() == clazz)
				return clazz.cast(control);
		return null;		
	}
	public Form_Ex 		wait_Seconds(final int seconds)
	{
		return wait_MiliSeconds(seconds * 1000);
	}
	public Form_Ex 		wait_MiliSeconds(final int miliSeconds)
	{
		final Semaphore semaphore = new Semaphore(1);
		Thread thread = new Thread(new Runnable() { public void run()
			{		
				try 
				{
					Thread.sleep(miliSeconds);
					semaphore.release();
				}
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}					
			}
		});
		thread.start();
		while (semaphore.availablePermits() ==1) 
		{
			display.syncExec(new Runnable() { public void run() 
						{
							if (!display.readAndDispatch())
								try 
								{
									Thread.sleep(100);
								}
								catch (InterruptedException e) 
								{
				
									e.printStackTrace();
								}
						}});
		}		
		return this;
	}
		
	public Browser_Ex   add_Browser()
	{
		return Browser_Ex.add_Browser(this.shell);
	}
	public Tree_Ex      add_Tree()
	{
		return Tree_Ex.add_Tree(this.shell);
	}
	
	public Form_Ex refresh()
	{
		display.syncExec(new Runnable() { public void run() 
					{
						shell.layout(true);
					}});
		return this;
	}
	public static Form_Ex popupWindow()
	{
		return new Form_Ex().layout_Fill()
						  .show();
	}
}
