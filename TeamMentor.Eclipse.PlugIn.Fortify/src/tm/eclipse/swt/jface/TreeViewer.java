package tm.eclipse.swt.jface;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

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
	
	
	public TreeItem add_Node(final String text)
	{
		return UIThreadRunnable.syncExec(display,new Result<TreeItem>() { public TreeItem run()
							{
								Tree tree = TreeViewer.this.getTree();
								TreeItem treeItem = new TreeItem(tree,SWT.NONE);
								treeItem.setText(text);								
								return treeItem;
							}});
	}
	
	public TreeViewer gridData_Fill()
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run()
			{
				GridData gridData = new GridData(SWT.FILL, SWT.FILL, true,true,1,1);
				TreeViewer.this.getTree().setLayoutData(gridData);
				target.layout(true);
			}});
		return this;
	}
	public List<TreeItem> nodes()
	{
		return UIThreadRunnable.syncExec(display,new Result<List<TreeItem>>() { public List<TreeItem> run()
		{
			return Arrays.asList(TreeViewer.this.getTree().getItems());
		}});		
	}
	
	public TreeViewer onDoubleClick(final Runnable runnable)
	{		
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run()
			{	
				TreeViewer.this.addDoubleClickListener(new IDoubleClickListener() {
				  @Override
				  public void doubleClick(DoubleClickEvent event) 
				  {
					  UIThreadRunnable.syncExec(display,new VoidResult() { public void run()
						{		
						  runnable.run();
						}});
				  }});
			}});
		return this;
	}
	public TreeViewer onSelection(final Runnable runnable)
	{
		UIThreadRunnable.syncExec(display,new VoidResult() { public void run()
			{	
				TreeViewer.this.getTree().addSelectionListener(new SelectionAdapter() {
					  @Override
					  public void widgetSelected(SelectionEvent e) 
					  {
						  UIThreadRunnable.syncExec(display,new VoidResult() { public void run()
							{		
							  runnable.run();
							}});
					  }});
			}});
/*		      @Override
		      public void doubleClick(DoubleClickEvent event) {
		        TreeViewer viewer = (TreeViewer) event.getViewer();
		        IStructuredSelection thisSelection = (IStructuredSelection) event
		            .getSelection();
		        Object selectedNode = thisSelection.getFirstElement();
		        viewer.setExpandedState(selectedNode,
		            !viewer.getExpandedState(selectedNode));
		      }
		    });*/
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
