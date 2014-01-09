package tm.eclipse.swt.controls;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;

public class Tree extends org.eclipse.swt.widgets.Tree
{	
	public Display display;
	
	public Tree(Composite parent, int style) 
	{		
		super(parent, style);
		this.display = parent.getDisplay();			// we need to store this in case there are multiple ones
	}	
	
	public static Tree add_Tree(final Composite target)
	{
		return add_Tree(target, SWT.None);
	}
	public static Tree add_Tree(final Composite target, final int style)
	{	
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Tree>() { public Tree run() 
					{
						Tree tree = new Tree(target, style);
						target.layout(true);
						return tree;
					}});
		
	}
	public TreeItem add_Node(final String text)
	{
		return UIThreadRunnable.syncExec(display,new Result<TreeItem>() { public TreeItem run()
							{
								TreeItem treeItem = new TreeItem(getThis(),SWT.NONE);
								treeItem.setText(text);								
								return treeItem;
							}});
	}
	
	public List<TreeItem> get_Nodes()
	{
		return UIThreadRunnable.syncExec(display,new Result<List<TreeItem>>() { public List<TreeItem> run()
			{
				return Arrays.asList(Tree.this.getItems());
			}});	
	}
	
	public Tree getThis()
	{
		return this;
	}
	
	protected void checkSubclass()
	{}
}
