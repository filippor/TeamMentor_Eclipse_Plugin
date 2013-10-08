package g2.scripts.editors;

import g2.groovy.api.TestGroovy;
import g2.java.api.TeamMentorAPI;
import g2.java.api.EclipseApi.EclipseAPI;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
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
	public Binding	    		 binding;
	public CompilerConfiguration configuration;
	public Composite   			 composite;
	public EclipseAPI			 eclipseApi;
	public Button   			 execute;
	public StyledText   		 result;
	public SashForm 			 sashForm;
	public EclipseAPI			 eclipseAPI;
	
	
	public void setBindingVariablesValues() 
	{
		eclipseAPI = new EclipseAPI();
		
		binding = new Binding();
				
		binding.setVariable("binding"	, binding);
		binding.setVariable("composite"	, composite);		
		binding.setVariable("editor"	, this);
		binding.setVariable("eclipseApi",eclipseAPI);		
		
	}
	public void setCompilerConfiguration()
	{
		configuration= new CompilerConfiguration();
		
		ImportCustomizer importCustomizer = new ImportCustomizer();
		importCustomizer.addStarImports("g2.java.api.eclipse.ui");
		configuration.addCompilationCustomizers(importCustomizer );
	}
	
	public void createPartControl(Composite _composite) 
	{
		composite  = _composite;
	    sashForm = new SashForm(composite, SWT.VERTICAL);
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
								
		setBindingVariablesValues();
		setCompilerConfiguration();
		
		TeamMentorAPI.mapGroovyBindings(binding);
		
		GroovyShell groovyShell = new GroovyShell(getClass().getClassLoader(),binding,configuration);
		
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
			String message = e.getMessage();
			result.setText("GENERIC ERROR:" + message + " : " + e.toString());
		}
	}	
}
