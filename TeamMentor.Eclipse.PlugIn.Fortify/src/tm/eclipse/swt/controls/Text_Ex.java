package tm.eclipse.swt.controls;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class Text_Ex extends Text
{	
	public Display _display;
	public SWTBotText swtBotText;
	
	public Text_Ex(Composite parent, int style) 
	{		
		super(parent, style);
		swtBotText = new SWTBotText(this);
		_display = parent.getDisplay();			// we need to store this in case there are multiple ones
	}	
		
	public static Text_Ex add_Text(final Composite target, final int style)
	{	
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Text_Ex>() { public Text_Ex run() 
					{
						Text_Ex text = new Text_Ex(target,style);
						target.layout(true);
						return text;
					}});
		
	}
	public static Text_Ex add_Text(final Composite target, final int style, String text)
	{
		Text_Ex textEx = add_Text(target,style);
		textEx.setText(text);
		return textEx;
	}
	public static Text_Ex add_Text(final Composite target)
	{
		return add_Text(target, SWT.None);
	}
	public static Text_Ex add_Text(final Composite target, String text)
	{
		return add_Text(target, SWT.None, text);
	}
	public static Text_Ex add_Text_Search(final Composite target)
	{
		return add_Text(target, SWT.SEARCH | SWT.ICON_SEARCH | SWT.CANCEL | SWT.BORDER);
	}
	
	public void setText(final String text)
	{
		UIThreadRunnable.syncExec(_display,new VoidResult() { public void run() 
		{
			Text_Ex.super.setText(text);			
		}});
	}
	public String getText()
	{
		return UIThreadRunnable.syncExec(_display,new Result<String>() { public String run() 
		{
			return Text_Ex.super.getText();			
		}});		
	}	
	
	protected void checkSubclass()
	{}
}
