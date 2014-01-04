package tm.eclipse.swt.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

public class Label extends org.eclipse.swt.widgets.Label
{
	public Display display;
	public Composite target;
	
	public static Label add_Label(Composite target)
	{
		return add_Label(target, SWT.None);
	}
	public static Label add_Label(Composite target, String text)
	{
		return add_Label(target, SWT.None, text);
	}	
	public static Label add_Label(final Composite target, final int style)
	{	
		if (target == null)
			return null;		
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Label>() { public Label run() 
					{
						Label label = new Label(target,style);
						return label.refresh();						
					}});
		
	}
	public static Label add_Label(final Composite target, final int style, String text)
	{
		return add_Label(target,style).text(text);		
	}
	
	public Label(Composite parent, int style) 
	{
		super(parent, style);
		this.display = parent.getDisplay();			// we need to store this in case there are multiple ones
		this.target  = parent;		
	}
	@Override
	public void setText(final String text)
	{
		if (text != null)
			UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
			{
				Label.super.setText(text);	
				Label.this.refresh();
			}});
	}
	@Override
	public String getText()
	{
		return UIThreadRunnable.syncExec(display,new Result<String>() { public String run() 
		{
			return Label.super.getText();			
		}});		
	}	
	public Label refresh()
	{
		if (target != null && display != null)			
			UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
			{
				target.layout(true);
			}});
					
		return this;
	}
	public String text()
	{
		return getText();
	}
	public Label text(String text)
	{
		setText(text);
		return this;
	}
	protected void checkSubclass()
	{}
}
