package tm.eclipse.swt.controls;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class Tree_Ex extends Tree
{	
	public Display _display;
	public SWTBotTree swtBotTree;
	
	public Tree_Ex(Composite parent, int style) 
	{		
		super(parent, style);
		swtBotTree = new SWTBotTree(this);
		_display = parent.getDisplay();			// we need to store this in case there are multiple ones
	}	
	
	public static Tree_Ex add_Tree(final Composite target)
	{
		return add_Tree(target, SWT.None);
	}
	public static Tree_Ex add_Tree(final Composite target, final int style)
	{	
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Tree_Ex>() { public Tree_Ex run() 
					{
						Tree_Ex tree = new Tree_Ex(target, style);
						target.layout(true);
						return tree;
					}});
		
	}
	public TreeItem add_Node(final String text)
	{
		return UIThreadRunnable.syncExec(_display,new Result<TreeItem>() { public TreeItem run()
							{
								TreeItem treeItem = new TreeItem(getThis(),SWT.NONE);
								treeItem.setText(text);								
								return treeItem;
							}});
	}
	
	public List<SWTBotTreeItem> get_Nodes()
	{
		return Arrays.asList(swtBotTree.getAllItems());
	}
	
	public Tree getThis()
	{
		return this;
	}
	
	protected void checkSubclass()
	{}
}
