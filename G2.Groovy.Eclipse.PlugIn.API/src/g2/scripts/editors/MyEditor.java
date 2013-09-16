package g2.scripts.editors;

import g2.groovy.api.TestGroovy;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
//
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public class MyEditor extends GroovyEditor 
{
	public SashForm 	sashForm;
	public Button   	execute;
	public StyledText   result;
	public Shell   	    shell;
	public Composite   	composite;
	public IWorkbench   workbench;
	public Display   	display;
	public TestGroovy   testGroovy;
	
	public void createPartControl(Composite parent) 
	{
	    sashForm = new SashForm(parent, SWT.VERTICAL);
	    sashForm.setLayout(new RowLayout());
	   
	    super.createPartControl(sashForm);
		
	    execute = new Button(sashForm, SWT.VERTICAL);
	    result  = new StyledText(sashForm, SWT.BORDER  | SWT.H_SCROLL | SWT.V_SCROLL);
	    result.setWordWrap(true);
	    
	    execute.setText("Compile and execute code");
	    execute.addSelectionListener(new SelectionAdapter() {public void widgetSelected(SelectionEvent event) 
						{
	    					compileAndExecuteCode();
						} });		
		result .setBackground(new Color(Display.getCurrent (),200,200,255));

		sashForm.setWeights(new int[] { 500,100,500});
		
		//for scripting
		shell 	   = parent.getShell();
		composite  = parent;
		workbench  = PlatformUI.getWorkbench();
		display    = workbench.getDisplay();		
		testGroovy = new TestGroovy();
	}
	public void compileAndExecuteCode()
	{		
		Display.getDefault().asyncExec(new Runnable() 
		{
	    	@Override
	    	public void run() 
	    	{
	    		compileAndExecuteCode_InGuiThread();
	    	}
		});
		
		
	
	}
	public void compileAndExecuteCode_InGuiThread()
	{
		String text = this.getDocumentProvider().getDocument(this.getEditorInput()).get();
				
		Binding binding = new Binding();		
		binding.setVariable("shell", shell);
		binding.setVariable("composite", composite);
		binding.setVariable("editor", this);
		binding.setVariable("workbench", workbench);
		binding.setVariable("display", display);
		binding.setVariable("testGroovy", testGroovy);
		
		GroovyShell groovyShell = new GroovyShell(getClass().getClassLoader(), binding);
		
		try 
		{				
			Object output = groovyShell.evaluate(text);
			result.setText(output != null ? output.toString() 
							              : "NULL return value");
							
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
