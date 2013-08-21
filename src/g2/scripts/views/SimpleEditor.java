package g2.scripts.views;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.*;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;


public class SimpleEditor extends ViewPart {
	
	public static final String ID = "g2.scripts.views.SimpleEditor";
	private StyledText code;
	private StyledText result;

	public SimpleEditor() 
	{
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) 
	{
		//MyEditor myEditor = new MyEditor();
		//myEditor.createPartControl(parent);
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
	    sashForm.setLayout(new RowLayout());

		
	    code  = new StyledText(sashForm, SWT.BORDER);
	    
	    Button execute = new Button(sashForm, SWT.VERTICAL);
	    result  = new StyledText(sashForm, SWT.BORDER);
	    	    
	    execute.setText("Compile and execute code");
	    execute.addSelectionListener(new SelectionAdapter() { public void widgetSelected(SelectionEvent event) 
						{
	    					compileAndExecuteCode();
						} });		
		result .setBackground(new Color(Display.getCurrent (),200,200,255));

		sashForm.setWeights(new int[] { 500,100,500});
		
		//for scripting
		//shell 	  = parent.getShell();
		//composite = parent;
		//workbench = PlatformUI.getWorkbench();
		//display   = workbench.getDisplay();
		
		
	}

	public void setFocus() 
	{
	//	viewer.getControl().setFocus();
	}
	
	public void compileAndExecuteCode() //_InGuiThread()
	{
		
	//	this.createActions();
			
	//	GroovyCompilationUnit compilationUnit = this.getGroovyCompilationUnit();
		String text = code.getText();
				
		Binding binding = new Binding();		
		binding.setVariable("shell", this.getSite().getShell());// shell);
		//binding.setVariable("composite", composite);
		binding.setVariable("editor", this);
		//binding.setVariable("workbench", workbench);
		//binding.setVariable("display", display);
		
		GroovyShell groovyShell = new GroovyShell(getClass().getClassLoader(), binding);
		
		
		//GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader());
		
		//groovyShell.getClassLoader().addClasspath("file:///E:/_Code_Tests/Java/eclipse/plugins/org.eclipse.jface_3.8.102.v20130123-162658.jar");
		try 
		{				
			String value = groovyShell.evaluate(text).toString();
			result.setText(value);
		} catch (CompilationFailedException e) 
		{					 
			result.setText("COMPILATION ERROR:" + e.getMessage());
		}
		catch(Exception e)
		{
			result.setText("GENERIC ERROR:" + e.getMessage());
		}
	}
}