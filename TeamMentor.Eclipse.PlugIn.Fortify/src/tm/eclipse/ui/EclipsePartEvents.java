package tm.eclipse.ui;

import groovy.lang.Closure;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

public class EclipsePartEvents implements IPartListener2
{
	Closure<IWorkbenchPartReference> Part_Activated;
	Closure<IWorkbenchPartReference> Part_Opened;
	
	@Override
	public void partActivated(IWorkbenchPartReference arg0) 
	{
		if (Part_Activated!=null)
			Part_Activated.call(arg0);
	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partClosed(IWorkbenchPartReference arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partDeactivated(IWorkbenchPartReference arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partHidden(IWorkbenchPartReference arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partInputChanged(IWorkbenchPartReference arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partOpened(IWorkbenchPartReference arg0) 
	{
		if (Part_Opened!=null)
			Part_Opened.call(arg0);
		
	}

	@Override
	public void partVisible(IWorkbenchPartReference arg0) {
		// TODO Auto-generated method stub
		
	}

}
