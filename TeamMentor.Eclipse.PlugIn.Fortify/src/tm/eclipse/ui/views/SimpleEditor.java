package tm.eclipse.ui.views;

import java.net.MalformedURLException;
import java.net.URL;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
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

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.ui.Activator;


public class SimpleEditor extends ViewPart 
{
	public static final String ID = "g2.scripts.views.SimpleEditor";
	
	public EclipseAPI			 eclipseApi;
	
	public SashForm   			 sashForm;		
	public Binding	    		 binding;
	public CompilerConfiguration configuration;
	public ImportCustomizer 	 importCustomizer;
	public Composite   			 composite;
	public GroovyShell 			 groovyShell;
	public Object				 output;
	public StyledText 			 styledText_Code;
	public StyledText 		     styledText_Result;
	public Button 				 execute_Button;
	
	public SimpleEditor() 
	{
		eclipseApi = Activator.eclipseApi;
	}

	public Binding setBindingVariablesValues() 
	{			
		binding = new Binding();				
		TeamMentorAPI.mapGroovyBindings(binding);
		return binding;	
	}
	public void addLocalVariablesToBinding()
	{
		binding.setVariable("binding"		  , binding			);
		binding.setVariable("configuration"	  , configuration	);
		binding.setVariable("composite"		  , composite		);		
		binding.setVariable("importCustomizer", importCustomizer);
		binding.setVariable("groovyShell"	  , groovyShell		);						
		binding.setVariable("view"			  , this			);	
		binding.setVariable("eclipseAPI"      , TeamMentorAPI.eclipseAPI);
		binding.setVariable("teammentorAPI"   , TeamMentorAPI.class);
	}
	public void setCompilerConfiguration()
	{
		configuration    = new CompilerConfiguration();		
		importCustomizer = new ImportCustomizer();
		importCustomizer.addStarImports("g2.java.api.eclipse.ui");
		configuration.addCompilationCustomizers(importCustomizer );
				
	}
	
	public GroovyShell create_and_Load_Jars_into_GroovyShell()
	{
		groovyShell = new GroovyShell(getClass().getClassLoader(),binding,configuration);
		
		for(String jar : eclipseApi.extraGroovyJars)
		{
			try
			{
				URL url = new URL("file://" + jar);							
				groovyShell.getClassLoader().addURL(url);
			}
			catch(MalformedURLException ex)				// I can't see to be able to trigger this, even with crazy values like: url = new URL("bb$%aa !@£$%^&*()_+{}[]|\"'?/><,.;'\\~`?|");
			{
				ex.printStackTrace();
			}			
		}		
		return groovyShell;
	}
	public URL[] get_GroovyShell_ClassLoader_Urls()
	{
		if (groovyShell != null)
			return groovyShell.getClassLoader().getURLs();			// (handle UnitTest exception)  java.lang.LinkageError: loader constraint violation:
		return null;
	}	
	public void createPartControl(Composite _composite) 
	{
		composite  = _composite;
		
		sashForm = new SashForm(_composite, SWT.VERTICAL);
	    sashForm.setLayout(new RowLayout());

		
	    styledText_Code   = new StyledText(sashForm, SWT.BORDER);
	    
	    execute_Button    = new Button(sashForm, SWT.VERTICAL);
	    styledText_Result = new StyledText(sashForm, SWT.BORDER  | SWT.H_SCROLL | SWT.V_SCROLL);
	    	    
	    execute_Button.setText("Compile and execute code");
	    execute_Button.addSelectionListener(new SelectionAdapter() { public void widgetSelected(SelectionEvent event) 
						{
	    					compileAndExecuteCode();
						} });		
	    styledText_Result.setBackground(new Color(Display.getCurrent (),200,200,255));

		sashForm.setWeights(new int[] { 500,100,500});
		
		styledText_Code.setText( "openArticle('Cross-Site Scripting')\n" +
				      			 "//openArticle('SQL Injection')\n" + 
					  			 "return eclipseAPI;");			
	}

	public void setFocus() 
	{
	//	viewer.getControl().setFocus();
	}
	
	public Object compileAndExecuteCode(String codeToExecute)
	{
		if(codeToExecute==null)
			return null;
		styledText_Code.setText(codeToExecute);
		return compileAndExecuteCode();
	}
	
	public Object compileAndExecuteCode() 
	{
		String text = styledText_Code.getText();
		
		setBindingVariablesValues();
		setCompilerConfiguration();
		
		//binding = new Binding();
		setBindingVariablesValues();						
		create_and_Load_Jars_into_GroovyShell();
		addLocalVariablesToBinding();
		
		try 
		{						
			output = groovyShell.evaluate(text);
			styledText_Result.setText(output != null ? output.toString() 
							                 : "NULL return value");
			return output;
		} 
		catch (CompilationFailedException e) 
		{					
			styledText_Result.setText("COMPILATION ERROR:" + e.getMessage());
		}
		catch(Exception e)
		{
			String message = e.getMessage();
			styledText_Result.setText("GENERIC ERROR:" + message + " : " + e.toString());
		}
		return null;
	}
}