package jar_using_groovy;

import java.net.MalformedURLException;
import java.net.URL;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.eclipse.editor.GroovyConfiguration;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
//
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.eclipse.ui.internal.browser.WorkbenchBrowserSupport;

public class MyEditor extends GroovyEditor 
{
	public SashForm 	sashForm;
	public Button   	execute;
	public StyledText   result;
	public Shell   	    shell;
	public Composite   	composite;
	public IWorkbench   workbench;
	public Display   	display;
	
	public void createPartControl(Composite parent) 
	{
	    sashForm = new SashForm(parent, SWT.VERTICAL);
	    sashForm.setLayout(new RowLayout());

	    super.createPartControl(sashForm);
	    execute = new Button(sashForm, SWT.VERTICAL);
	    result  = new StyledText(sashForm, SWT.BORDER);
	    	    
	    execute.setText("Compile and execute code");
	    execute.addSelectionListener(new SelectionAdapter() {public void widgetSelected(SelectionEvent event) 
						{
	    					compileAndExecuteCode();
						} });		
		result .setBackground(new Color(Display.getCurrent (),200,200,255));

		sashForm.setWeights(new int[] { 500,100,500});
		
		//for scripting
		shell 	  = parent.getShell();
		composite = parent;
		workbench = PlatformUI.getWorkbench();
		display   = workbench.getDisplay();
		
		
		/*try 
		{
			int style = IWorkbenchBrowserSupport.AS_EDITOR | IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.STATUS;
			IWebBrowser browser;
			browser = WorkbenchBrowserSupport.getInstance().createBrowser(style, "MyBrowserID", "MyBrowserName", "MyBrowser Tooltip");
			browser.openURL(new URL("http://www.google.com"));	
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	public void compileAndExecuteCode()
	{
		
		//Display display = new Display();
		//Shell shell2 = new Shell(display);
		
		//shell2.open();
		
		/*try {
			PlatformUI.getWorkbench().openWorkbenchWindow("asda", null);
		} catch (WorkbenchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Display.getDefault().asyncExec(new Runnable() 
		{
	    	@Override
	    	public void run() 
	    	{
	    		compileAndExecuteCode_InGuiThread();
	    			
	    		//IMenuManager menuManager = win.getActionBars().getMenuManager();
	    		
	    	/*WorkbenchWindow win = (WorkbenchWindow) PlatformUI.getWorkbench().getWorkbenchWindows()[0];
	        IMenuManager menuManager = win.getActionBars().getMenuManager();
	        IMenuManager menu = new MenuManager("Editor &Menu");
	        menuManager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
	        
	    	Action sampleAction = new Action("test action") {
				public void run() {
					MessageDialog.openInformation(null, "Jar_using_Groovy", "Sample Action Executed"); 
				}};
			
			menu.add(sampleAction);*/
	    	}
		});
		
		
	
	}
	public void compileAndExecuteCode_InGuiThread()
	{
		
	//	this.createActions();
			
	//	GroovyCompilationUnit compilationUnit = this.getGroovyCompilationUnit();
		String text = this.getDocumentProvider().getDocument(this.getEditorInput()).get();
				
		Binding binding = new Binding();		
		binding.setVariable("shell", shell);
		binding.setVariable("composite", composite);
		binding.setVariable("editor", this);
		binding.setVariable("workbench", workbench);
		binding.setVariable("display", display);
		
		GroovyShell groovyShell = new GroovyShell(getClass().getClassLoader(), binding);
		
		
		//GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader());
		
		//groovyShell.getClassLoader().addClasspath("file:///E:/_Code_Tests/Java/eclipse/plugins/org.eclipse.jface_3.8.102.v20130123-162658.jar");
		try 
		{				
			String value = groovyShell.evaluate(text).toString();
			result.setText(value);
		} catch (CompilationFailedException e) 
		{					
			// TODO Auto-generated catch block
			result.setText("COMPILATION ERROR:" + e.getMessage());
		}
		catch(Exception e)
		{
			result.setText("GENERIC ERROR:" + e.getMessage());
		}
//			Menu menubar = shell.getMenuBar();
		//menubar.getItems().add(new MenuItem("aa"));
//			MenuItem menu = shell.getMenuBar().getItem(0);
		//shell.getMenu().add(new MenuManager("Menu &1","1"));
				
		//result.setText(compilationUnit.toStringWithAncestors()ing());
	}
}
