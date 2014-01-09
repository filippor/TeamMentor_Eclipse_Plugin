package tm.eclipse.swt.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

import tm.eclipse.swt.Add_Composite;
import tm.eclipse.swt.Get_Composite;
import tm.eclipse.swt.Set_Composite;

public class Canvas extends org.eclipse.swt.widgets.Canvas
{
	public Display display;
	public org.eclipse.swt.widgets.Composite target;
	public Composite composite;
	public Add_Composite<Composite> add;
	public Get_Composite<Composite> get;
	public Set_Composite<Composite> set;
			
	public Canvas(org.eclipse.swt.widgets.Composite parent, int style) 
	{
		super(parent, style);
		this.display = parent.getDisplay();
		this.composite = new Composite(this); // need to wrap the current shell's composite into our own Composite 
		add = new Add_Composite<Composite>(composite);
		get = new Get_Composite<Composite>(composite);
		set = new Set_Composite<Composite>(composite);				
	}	
	
	public static Canvas add_Canvas(final Composite target, final int style)
	{	
		if (target == null)
			return null;		
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Canvas>() { public Canvas run() 
					{
						Canvas canvas = new Canvas(target,style);
						canvas.setLayout(new FillLayout());
						return canvas.refresh();						
					}});
		
	}
	
	public Canvas refresh()
	{
		if (target != null && display != null)			
			UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
			{
				target.layout(true);
			}});
					
		return this;
	}

	protected void checkSubclass()
	{}
}