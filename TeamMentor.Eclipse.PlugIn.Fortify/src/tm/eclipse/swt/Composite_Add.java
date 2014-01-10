package tm.eclipse.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

import tm.eclipse.helpers.Images;
import tm.eclipse.swt.controls.Button;
import tm.eclipse.swt.controls.Canvas;
import tm.eclipse.swt.controls.Composite;
import tm.eclipse.swt.controls.Group;
import tm.eclipse.swt.controls.Label;
import tm.eclipse.swt.controls.Text;
import tm.eclipse.swt.controls.ToolBar;
import tm.eclipse.swt.jface.TreeViewer;

public class Composite_Add <T extends Composite>
{
	public T target;
	public Display   display;
	
	public Composite_Add(T target)
	{
		this.target  = target;
		this.display = target.getDisplay();
	}
	
	public Button button()
	{
		return button((String)null);
	}
	public Button button(Image image)
	{
		return Button.add_Button(target).image(image);		
	}
	public Button button(String text)
	{
		return Button.add_Button(target).text(text);		
	}
	public Button button(String text, Image image)
	{
		return Button.add_Button(target).text(text).image(image);		
	}
	public Canvas canvas()
	{
		return Canvas.add_Canvas(target, SWT.NONE);		
	}
	public Button checkBox(String text)
	{
		return Button.add_Button(target,SWT.CHECK).text(text);		
	}
	public Composite composite()
	{
		return Composite.add_Composite(target);
	}
	public Group group()
	{
		return Group.add_Group(target, SWT.NONE);		
	}
	public Label image(String imageKey)
	{
		Image image = Images.get(imageKey);
		if (image != null)
			return image(image);
		return null;
	}
	public Label image(Image image)
	{
		return label().image(image);		
	}
	public Label image(Image image, String toolTip)
	{
		return label().image(image).toolTip(toolTip);		
	}
	public Label label()
	{
		return label((String)null);
	}
	public Label label(Image image)
	{
		return label().image(image);		
	}
	public Label label(String text)
	{
		return label(text, SWT.NONE);
	}
	public Label label(String text, int style)
	{
		return Label.add_Label(target,style).text(text);		
	}
	public Composite panel()
	{
		return composite();
	}
	public T refresh()
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			target.layout(true);
		}});
		return target;
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
	public Text text()
	{
		return text(null);
	}
	public Text text(String text)
	{
		return Text.add_Text(target).text(text);		
	}
	public Text textArea(String text)
	{
		return Text.add_Text_MultiLine(target,text);		
	}
	public TreeViewer treeViewer()
	{
		return TreeViewer.add_TreeViewer(target);
	}
	public ToolBar toolBar()
	{
		return ToolBar.add_ToolBar(target);		
	}
}
