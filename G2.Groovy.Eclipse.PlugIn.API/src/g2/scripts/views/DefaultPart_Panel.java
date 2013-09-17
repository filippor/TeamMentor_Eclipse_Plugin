package g2.scripts.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class DefaultPart_Panel extends ViewPart 
{
	public static final String ID = "g2.scripts.views.DefaultPart_Panel";
	public  Composite panel;
	
	public void createPartControl(Composite parent) 
	{
		panel  = parent;
	}
	public void setFocus() 
	{
	}	
}