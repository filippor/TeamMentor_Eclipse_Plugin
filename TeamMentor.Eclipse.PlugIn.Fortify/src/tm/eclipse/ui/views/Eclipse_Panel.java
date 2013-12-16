package tm.eclipse.ui.views;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;

import tm.swt.controls.*;

public class Eclipse_Panel extends ViewPart 
{
	public static final String ID = "tm.eclipse.ui.views.Eclipse_Panel";
	public Composite composite;
	
	//required implementations
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
	
	//helper methods
	public Browser_Ex add_WebBrowser()
	{
		return new Browser_Ex(composite,SWT.BORDER);	    	    
	}	 
	public Tree_Ex 	  add_Tree()
	{
		
		return new Tree_Ex(composite,SWT.BORDER);	    	    
	}
		
	public List<Control> controls()
	{
		return Arrays.asList(composite.getChildren());
	}	
	public <T> T 		 control(Class<T> clazz)
	{
		for(Control control : controls())
			if (control.getClass() == clazz)
				return clazz.cast(control);
		return null;		
	}
	public Eclipse_Panel refresh()
	{
		composite.getDisplay().syncExec(new Runnable() { public void run() 
					{
						composite.layout(true);
					}});
		return this;
	}
}