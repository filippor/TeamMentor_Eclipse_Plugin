package g2.scripts.editors;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import org.codehaus.groovy.control.CompilationFailedException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.*;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.ide.IDE;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MultiPageEditor extends MultiPageEditorPart implements IResourceChangeListener{

	/** The text editor used in page 0. */
	public TextEditor editor;

	/** The font chosen in page 1. */
	public Font font;

	/** The text widget used in page 2. */
	public StyledText text;
	
	public StyledText code;
	public StyledText executionResult;
	public Shell shell;
	public MyEditor myEditor;
	/**
	 * Creates a multi-page editor example.
	 */
	
	
	
	public MultiPageEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}
	/**
	 * Creates page 0 of the multi-page editor,
	 * which contains a text editor.
	 */
	void createPage0() {
		try 
		{				
			myEditor = new MyEditor();			
			int index = addPage(myEditor, getEditorInput());
			String title = myEditor.getTitle();
			setPageText(index, "G2 Script Editor");			
			setPartName(title);			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	// setPageText(index, editor1.getTitle());
	
	/**
	 * Creates page 1 of the multi-page editor,
	 * which allows you to change the font used in page 2.
	 */
/*	void createPage1() 
	{
		Composite composite = new Composite(getContainer(), SWT.NONE);
		
		
		
		shell = composite.getShell();
		
		GridLayout gridLayout = new GridLayout();
		
		composite.setLayout(gridLayout);
		
		code = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		code.setEditable(true);
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = GridData.FILL;
		gridData1.verticalAlignment = GridData.FILL;
		gridData1.horizontalSpan = 3;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		code.setLayoutData(gridData1);
		
		 
		
		Button testButton = new Button(composite, SWT.NONE);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		testButton.setLayoutData(gridData);
		testButton.setText("Compile code");
		
		
		StyledText test1 = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		//GroovyEditor test1 = new GroovyEditor();
		Color backG = test1.getBackground(); 
		Color backG2 = new Color(backG.getDevice(),200,200,255);
		test1.setBackground(backG2);
		GridData gridData3 = new GridData();
		gridData3.horizontalAlignment = GridData.FILL;
		gridData3.verticalAlignment = GridData.FILL;
		gridData3.grabExcessVerticalSpace = true;
		
		test1.setLayoutData(gridData3);
		
		
		executionResult = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		executionResult.setEditable(false);
		GridData gridData2 = new GridData();
		gridData2.horizontalAlignment = GridData.FILL;
		gridData2.verticalAlignment = GridData.FILL;
		gridData2.horizontalSpan = 3;
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.grabExcessVerticalSpace = true;
		executionResult.setLayoutData(gridData2);
		
		testButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) 
			{
				
				String text = code.getText();
				
				/ *MessageDialog.openInformation(
						getSite().getShell(),
						"The text below is",
						text);* /
				Binding binding = new Binding();
				binding.setVariable("foo", new Integer(2));
				binding.setVariable("shell", shell);
				binding.setVariable("editor", editor);
				binding.setVariable("result", executionResult);
				binding.setVariable("myEditor", myEditor);
								
				GroovyShell shell = new GroovyShell(binding);

				try 
				{				
					String value = shell.evaluate(text).toString();
					executionResult.setText(value);
				} catch (CompilationFailedException e) 
				{					
					// TODO Auto-generated catch block
					executionResult.setText("COMPILATION ERROR:" + e.getMessage());
				}
				catch(Exception e)
				{
					executionResult.setText("GENERIC ERROR:" + e.getMessage());
				}				
			}			
		});
		
		int index = addPage(composite);
		setPageText(index, "My Layout test");
		
		if(true)
			return;
			
	}
	*/
	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() 
	{
		createPage0();
//		createPage1();
		
				
	}
	/**
	 * The <code>MultiPageEditorPart</code> implementation of this 
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
		throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
		{
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		}
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}
	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() 
	{
		
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}
	
	
	/*void myTests()
	{
		/ *MessageDialog.openInformation(
				getSite().getShell(),
				"Hellogroovyworld Plug-in",
				"Hello, Groovy world");* /
		String text = editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();
		Binding binding = new Binding();
		binding.setVariable("foo", new Integer(2));
		binding.setVariable("bar", new String(text));
		GroovyShell shell = new GroovyShell(binding);

		String value = shell.evaluate("println bar; println 'Hello World!'; x = 123; return foo * 20").toString();
		
		MessageDialog.openInformation(
				getSite().getShell(),
				"The execution result was",
				text + "   " +  value);
	}	*/
}
