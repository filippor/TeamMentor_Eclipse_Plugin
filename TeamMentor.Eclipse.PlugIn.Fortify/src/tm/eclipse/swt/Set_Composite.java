package tm.eclipse.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

public class  Set_Composite<T extends Composite>  
{	
	public T target;
	public Display   display;
	
	public Set_Composite(T target)
	{
		this.target  = target;
		this.display = target.getDisplay();
	}
	
	public T layout(final Layout layout)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
		{
			target.setLayout(layout);			
		}});
		return (T)target;
	}
	public T layout_Grid()
	{
		return layout_Grid(1,false);
	}
	public T layout_Grid(int numColumns)
	{
		return layout_Grid(numColumns,false);
	}
	public T layout_Grid(int numColumns, boolean makeColumnsEqualWidth)
	{		
		return layout(new GridLayout(numColumns, makeColumnsEqualWidth));
	}
	public T layout_Fill()
	{
		return layout_Fill(true);
	}
	public T layout_Fill(boolean horizontalLayout)
	{
		int alignment = (horizontalLayout) ? SWT.HORIZONTAL : SWT.VERTICAL;
		return layout(new FillLayout(alignment));
	}
	public T layout_Row()
	{
		return layout_Row(true);
	}
	public T layout_Row(boolean horizontalLayout)
	{
		int alignment = (horizontalLayout) ? SWT.HORIZONTAL : SWT.VERTICAL;
		return layout(new RowLayout(alignment));
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
	
	/*public T rowLayout()
	{
		return this.layout_Row();
	}
	public T fillLayout()
	{	
		return this.layout_Fill();
	}*/
	
	
}
