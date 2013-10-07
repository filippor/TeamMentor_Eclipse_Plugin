package g2.java.api.eclipse.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import g2.java.api.EclipseAPI;
import g2.scripts.views.DefaultPart_Panel;
import g2.scripts.views.DefaultPart_WebBrowser;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class eclipse_Window extends EclipseAPI
{
	public DefaultPart_Panel panel;
	public String    panelId; 
	
	public eclipse_Window() throws PartInitException 
	{
		panelId = String.valueOf(Math.abs(new Random().nextInt()));
		createWindow();
	}
	public eclipse_Window(String _panelId) throws PartInitException
	{		
		panelId = _panelId;		
		createWindow();
	}
	
	public eclipse_Window createWindow() throws PartInitException
	{
		panel = (DefaultPart_Panel)activePage().showView(DefaultPart_Panel.ID, panelId, IWorkbenchPage.VIEW_ACTIVATE);
		title(panelId);
		return this;
	}
	public String title()
	{
		return panel.getPartName();
	}
	public eclipse_Window title(String title)
	{
		panel.title(title);
		return this;		
	}

	public List<Control> children()
	{
		return Arrays.asList(panel.composite.getChildren());
	}
	
	public Browser add_WebBrowser()
	{
		Browser browser  = new Browser(panel.composite,SWT.BORDER);
	    
	    browser.setText(DefaultPart_WebBrowser.DEFAULT_HTML);	    
	    panel.composite.layout();									//redraw doesn't work
	    return browser;
	}
	
	public TreeViewer add_TreeView()
	{
		TreeViewer treeViewer  = new TreeViewer(panel.composite);
		panel.composite.layout();
	    return treeViewer;
	}
		
	
	public static TreeItem add_Node(Tree tree, String text)
	{
		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText(text);
		return treeItem;
	}
	
	
	
}
