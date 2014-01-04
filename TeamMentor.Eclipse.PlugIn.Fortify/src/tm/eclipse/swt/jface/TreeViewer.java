package tm.eclipse.swt.jface;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;

public class TreeViewer extends org.eclipse.jface.viewers.TreeViewer
{
	public Display display;
	public Composite target;
		
	public TreeViewer(Composite parent)
	{
		super(parent);
		setTarget(parent);	
	}
	public TreeViewer(Composite parent, int style) 
	{
		super(parent, style);
		setTarget(parent);
		
	}
	public TreeViewer(Tree tree)
	{
		super(tree);
		setTarget(tree.getParent());
	}
	
	public static TreeViewer add_TreeViewer(final Composite target)
	{
		if (target == null)
			return null;		
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<TreeViewer>() { public TreeViewer run() 
					{
						return new TreeViewer(target);						
					}});
	}
	
	public TreeViewer setTarget(Composite target)
	{
		this.target  = target;
		this.display = this.target.getDisplay();
		return this;
	}
	
	/*public void refresh()
	{
		if (target != null && display != null)			
			UIThreadRunnable.syncExec(display,new VoidResult() { public void run() 
			{
				target.layout(true);
			}});
					
		return this;
	}*/
	
}
