package tm.eclipse.ui.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class Eclipse_Panel extends ViewPart 
{
	public static final String ID = "tm.eclipse.ui.views.Eclipse_Panel";
	public Composite composite;
	
	public void createPartControl(Composite parent) 
	{
		composite  = parent;
	}
	public void setFocus() 
	{
	}	
	
	public void title(String title)
	{
		super.setPartName(title);
	}
}