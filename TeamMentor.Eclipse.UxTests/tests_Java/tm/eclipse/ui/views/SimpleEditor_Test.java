package tm.eclipse.ui.views;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.ArrayList;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.junit.Test;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.api.TeamMentorAPI;
import tm.eclipse.ui.Activator;



public class SimpleEditor_Test 
{	
	public SimpleEditor simpleEditorView;
	public EclipseAPI   eclipseAPI;
	
	public SimpleEditor_Test()
	{		
		eclipseAPI = Activator.eclipseApi;
		
		IViewPart viewPart = null;
		try 
		{
			viewPart = eclipseAPI.activePage().showView(SimpleEditor.ID);
		} 
		catch (PartInitException e) 
		{
			e.printStackTrace();
		}
		assertNotNull(viewPart);
		simpleEditorView = (SimpleEditor) viewPart;
		assertNotNull(simpleEditorView);				
	}
	
	@Test
	public void SimpleEditor_Ctor()
	{
		assertNotNull(simpleEditorView.eclipseApi);
		assertNotNull(simpleEditorView.sashForm);
		assertNotNull(simpleEditorView.styledText_Code);
		assertNotNull(simpleEditorView.styledText_Result);
		assertNotNull(simpleEditorView.composite);
		
		assertNull(simpleEditorView.binding);
		assertNull(simpleEditorView.configuration);
		assertNull(simpleEditorView.importCustomizer);		
		assertNull(simpleEditorView.groovyShell);
		assertNull(simpleEditorView.output);		
	}
	@Test
	public void get_GroovyShell_ClassLoader_Urls()
	{
		simpleEditorView.groovyShell= null; 
		assertNull(simpleEditorView.get_GroovyShell_ClassLoader_Urls());
		create_and_Load_Jars_into_GroovyShell();
		assertNotNull(simpleEditorView.get_GroovyShell_ClassLoader_Urls());
	}
	@Test 
	public void setBindingVariablesValues()
	{
		Binding binding = simpleEditorView.setBindingVariablesValues();
		
		assertNotNull(binding);	
		assertNotNull(simpleEditorView.binding);
		
		//these throws: java.lang.LinkageError: loader constraint violation: loader (instance of org/eclipse/osgi/internal/baseadaptor/DefaultClassLoader) previously initiated loading for a different type with name "groovy/lang/Binding"		
		//Map variables = binding.getVariables();  		
		/*assertNotNull(variables);
		  assertEquals(10,variables.size());		
		  assertNotNull(binding.getVariable("openArticle"));
		  assertNotNull(binding.getVariable("loginIntoTM"));		
		  assertNull(binding.getVariable("ABCDEFG"));
		*/  	
	}
	@Test 
	public void setCompilerConfiguration()
	{		
		simpleEditorView.setCompilerConfiguration();
		assertNotNull(simpleEditorView.configuration);
		assertNotNull(simpleEditorView.importCustomizer);
	}
	
	@Test
	public void create_and_Load_Jars_into_GroovyShell()
	{
		setBindingVariablesValues();
		setCompilerConfiguration();
		
		//first when there are no extraGroovyJars
		assertEquals(0,simpleEditorView.eclipseApi.extraGroovyJars.size());
		GroovyShell first_GroovyShell = simpleEditorView.create_and_Load_Jars_into_GroovyShell();
		assertNotNull(simpleEditorView.groovyShell);	
		URL[] urls = simpleEditorView.get_GroovyShell_ClassLoader_Urls();
		assertEquals(0,urls.length);
		
		//add and extraGroovyJars and check that it is there
		String testJar = "/abc/123.jar";
		simpleEditorView.eclipseApi.extraGroovyJars.add(testJar);	
		GroovyShell second_GroovyShell = simpleEditorView.create_and_Load_Jars_into_GroovyShell();		
		urls = simpleEditorView.get_GroovyShell_ClassLoader_Urls();
		assertEquals(1,urls.length);
		assertNotEquals(first_GroovyShell, second_GroovyShell);

		/*simpleEditorView.eclipseApi.extraGroovyJars.add("bb$%aa");		// this is not throwing the exception
		  simpleEditorView.create_and_Load_Jars_into_GroovyShell();
		*/
		
		//reset extraGroovyJars		
		simpleEditorView.eclipseApi.extraGroovyJars = new ArrayList<String>();
	}
	
	@Test
	public void compileAndExecuteCode()
	{
		String testGroovy     = "40+2";
		String expectedResult = "42";
		
		String originalCode = simpleEditorView.styledText_Code.getText();
		assertNotNull(originalCode);		
		
		// set simple Groovy to test execution		
		simpleEditorView.styledText_Code.setText(testGroovy);		
		assertEquals   (testGroovy, simpleEditorView.styledText_Code.getText());
		Object output  = simpleEditorView.compileAndExecuteCode();
		assertNotNull  (simpleEditorView.output);
		assertEquals   (output, simpleEditorView.output);
		assertEquals   (expectedResult, output.toString());
		
		//check that 42 != 43 :)
		assertNotEquals(simpleEditorView.compileAndExecuteCode("40+2"), 43);
	}
	
	@Test
	public void groovy_Execution_ViaButton()
	{
		simpleEditorView.styledText_Result.setText("");
		simpleEditorView.styledText_Code  .setText("30+10+2");
		
		//checks that there is one listener
		Listener[] listeners =  simpleEditorView.execute_Button.getListeners(SWT.Selection);
		assertNotNull(listeners);
		assertEquals(1,listeners.length);
		
		//invokes the button and checks the results
		simpleEditorView.execute_Button.notifyListeners(SWT.Selection, null);
		assertEquals(simpleEditorView.styledText_Result.getText(), "42");
		assertEquals(simpleEditorView.output.toString()        , "42");
	}
	@Test
	public void groovy_Execution_Check_Binded_Variables()
	{
		assertEquals   (simpleEditorView.compileAndExecuteCode("40+2"        	  ), 42);
		assertEquals   (simpleEditorView.compileAndExecuteCode("40+2-10+12-2"	  ), 42);
		assertEquals   (simpleEditorView.compileAndExecuteCode("(40+2).toString()"),"42");
		assertEquals   (simpleEditorView.compileAndExecuteCode("return eclipseApi"), null);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return null"      ), null);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return binding"   	 	), simpleEditorView.binding	  		);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return configuration"	), simpleEditorView.configuration	);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return composite"	 	), simpleEditorView.composite	  	);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return importCustomizer"), simpleEditorView.importCustomizer);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return groovyShell"		), simpleEditorView.groovyShell	  	);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return view"	  	 	), simpleEditorView			  		);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return eclipseAPI"	 	), simpleEditorView.eclipseApi	  	);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return teammentorAPI"	), TeamMentorAPI.class	  			);
		
		assertEquals   (simpleEditorView.compileAndExecuteCode("return eclipseAPI.workbench   "), simpleEditorView.eclipseApi.workbench);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return eclipseAPI.workspace   "), simpleEditorView.eclipseApi.workspace);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return eclipseAPI.display	  "), simpleEditorView.eclipseApi.display);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return eclipseAPI.activePage()"), simpleEditorView.eclipseApi.activePage());
		
		
		assertEquals   (simpleEditorView.compileAndExecuteCode(null),null);
		assertEquals   (simpleEditorView.compileAndExecuteCode("!@£$"),null);
		assertEquals   (simpleEditorView.compileAndExecuteCode("return nonExistentVariable"),null);
	}
}
