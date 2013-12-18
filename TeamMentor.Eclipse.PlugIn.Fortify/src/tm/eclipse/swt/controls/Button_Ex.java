package tm.eclipse.swt.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;


public class Button_Ex extends Button
{	
	public Display _display;
	public SWTBotButton swtBotButton;
	
	public Button_Ex(Composite parent, int style) 
	{		
		super(parent, style);
		swtBotButton = new SWTBotButton(this);
		_display = parent.getDisplay();			// we need to store this in case there are multiple ones
	}	
	
	public static Button_Ex add_Button(final Composite target, final int style, String text)
	{		
		Button_Ex button = add_Button(target, style);
		button.setText(text);
		return button;
	}
	public static Button_Ex add_Button(final Composite target, final int style)
	{	
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Button_Ex>() { public Button_Ex run() 
					{
						Button_Ex button = new Button_Ex(target,style);
						target.layout(true);
						return button;
					}});
		
	}	
	public static Button_Ex add_Button(final Composite target)
	{
		return add_Button(target, SWT.None);
	}	
	public static Button_Ex add_Button(final Composite target, String text)
	{
		return add_Button(target, SWT.None, text);
	}
	
	public Button_Ex onClick(final Runnable runnable)
	{
		this.addListener(SWT.Selection, new Listener() { public void handleEvent(Event event) 
			{
				runnable.run();
			}});
		return this; 
	}

	//overrides
	public void   setImage(final Image image)
	{
		UIThreadRunnable.syncExec(_display,new VoidResult() { public void run() 
		{
			Button_Ex.super.setImage(image);			
		}});
	}
	public void   setText(final String text)
	{
		UIThreadRunnable.syncExec(_display,new VoidResult() { public void run() 
		{
			Button_Ex.super.setText(text);			
		}});
	}
	public String getText()
	{
		return UIThreadRunnable.syncExec(_display,new Result<String>() { public String run() 
		{
			return Button_Ex.super.getText();			
		}});		
	}	
	
	protected void checkSubclass()
	{}
}
