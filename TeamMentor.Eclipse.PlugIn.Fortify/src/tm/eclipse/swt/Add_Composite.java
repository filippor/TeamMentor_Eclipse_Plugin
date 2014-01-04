package tm.eclipse.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

import tm.eclipse.swt.controls.Button;
import tm.eclipse.swt.controls.Label;
import tm.eclipse.swt.controls.Text;
import tm.eclipse.swt.jface.TreeViewer;

public class Add_Composite <T extends Composite>
{
	public T target;
	public Display   display;
	
	public Add_Composite(T target)
	{
		this.target  = target;
		this.display = target.getDisplay();
	}
	
	public Button button()
	{
		return button(null);
	}
	public Button button(String text)
	{
		return Button.add_Button(target).text(text);		
	}
	
	public Text text()
	{
		return text(null);
	}
	public Text text(String text)
	{
		return Text.add_Text(target).text(text);		
	}
	
	
	public Label label()
	{
		return label(null);
	}
	public Label label(String text)
	{
		return label(text, SWT.NONE);
	}
	public Label label(String text, int style)
	{
		return Label.add_Label(target,style).text(text);		
	}
	
	public Label separator()
	{
		return separator(true);
	}
	public Label separator(boolean horizontalLayout)
	{
		int alignment = (horizontalLayout) ? SWT.HORIZONTAL : SWT.VERTICAL;
		return label(null, SWT.SEPARATOR | alignment);
	}
	
	public TreeViewer treeViewer()
	{
		return TreeViewer.add_TreeViewer(target);
	}
	public T refresh()
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			target.layout(true);
		}});
		return target;
	}
	
}
