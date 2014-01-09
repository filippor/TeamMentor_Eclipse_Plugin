package tm.eclipse.swt.controls;

//import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;

import tm.eclipse.swt.Add_Composite;
import tm.eclipse.swt.Get_Composite;
import tm.eclipse.swt.Mouse;
import tm.eclipse.swt.Set_Composite;

public class Composite extends org.eclipse.swt.widgets.Composite
{
	public org.eclipse.swt.widgets.Composite parent;
	public Mouse 					mouse;
	public Add_Composite<Composite> add;
	public Get_Composite<Composite> get;
	public Set_Composite<Composite> set;

	public Composite(org.eclipse.swt.widgets.Composite parent)
	{		
		this(parent, SWT.NONE);		
	}
	public Composite(org.eclipse.swt.widgets.Composite parent, int style) 
	{
		super(parent, style);
		this.parent = parent;
		mouse = new Mouse(this.getDisplay());
		add   = new Add_Composite<Composite>(this);
		set   = new Set_Composite<Composite>(this);
		get   = new Get_Composite<Composite>(this);
		
		Layout layout = this.getLayout();
		if (layout == null)
		{
			setLayout(new FillLayout());
		}
	}
	
	public static Composite add_Composite(final Composite target)
	{	
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Composite>() { public Composite run() 
					{
						Composite composite = new Composite(target);
						target.layout(true);
						return composite;
					}});
		
	}


}
