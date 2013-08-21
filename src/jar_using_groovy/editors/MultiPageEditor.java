package jar_using_groovy.editors;


import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import jar_using_groovy.MyEditor;

import java.awt.MenuBar;
import java.io.StringWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
	private TextEditor editor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private StyledText text;
	
	private StyledText code;
	private StyledText executionResult;
	private Shell shell;
	private MyEditor myEditor;
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
			//editor = new TextEditor();
			
			
			//GroovyEditor groovyEditor = new GroovyEditor();
			//int index = addPage(groovyEditor,groovyEditor.getEditorInput());
			//int index = addPage(editor, getEditorInput());
			//setPageText(index, editor.getTitle());
			
			//GroovyEditor editor1 = new GroovyEditor();
			
			myEditor = new MyEditor();			
			int index = addPage(myEditor, getEditorInput());
			setPageText(index, "MyEditor");
			
			//GridLayout gridLayout = new GridLayout();
			
			//composite.setLayout(gridLayout);
			
			
			
			//int index = addPage(composite);
			//setPageText(index, "My Layout test");
			
//			editor1.createPartControl(sashForm);
			
				//int index = addPage(editor1, getEditorInput());
			//setPageText(index, editor1.getTitle());
			//groovyEditor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
		}
		//catch (PartInitException e) 
		catch (Exception e)
		{
			e.printStackTrace();
			/*ErrorDialog.openError(
				getSite().getShell(),
				"Error creating nested text editor",
				null,
				e.getStatus());*/
		}
	}
	/**
	 * Creates page 1 of the multi-page editor,
	 * which allows you to change the font used in page 2.
	 */
	void createPage1() 
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
				
				/*MessageDialog.openInformation(
						getSite().getShell(),
						"The text below is",
						text);*/
				Binding binding = new Binding();
				binding.setVariable("foo", new Integer(2));
				binding.setVariable("shell", shell);
				binding.setVariable("editor", editor);
				binding.setVariable("result", executionResult);
				binding.setVariable("myEditor", myEditor);
				
	//			Menu menubar = shell.getMenuBar();
				//menubar.getItems().add(new MenuItem("aa"));
	//			MenuItem menu = shell.getMenuBar().getItem(0);
				//shell.getMenu().add(new MenuManager("Menu &1","1"));
				
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
		//this.editor
		
		/*final Browser browser;
		try {
			browser = new Browser(composite, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			
			return;
		}
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.horizontalSpan = 3;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		browser.setLayoutData(data);
		
		browser.setUrl("https://www.google.com");*/
		if(true)
			return;
		
	/*	Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;

		Button fontButton = new Button(composite, SWT.NONE);
		GridData gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 2;
		fontButton.setLayoutData(gd);
		fontButton.setText("Change Font...");
		
		fontButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setFont();
			}
		});
		
		
		Button testButton = new Button(composite, SWT.NONE);				
		testButton.setLayoutData(gd);
		testButton.setText("My Test!!!!!");
		
		testButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) 
			{
               
				myTests();
			}
		});
		
		Button anotherButton = new Button(composite, SWT.NONE);				
		anotherButton.setLayoutData(gd);
		anotherButton.setText("Another button that does nothing");
		
		int index = addPage(composite);
		setPageText(index, "Properties 123");
		*/
	}
	/**
	 * Creates page 2 of the multi-page editor,
	 * which shows the sorted text.
	 */
	void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEditable(false);

		int index = addPage(composite);
		setPageText(index, "Preview");
	}
	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage0();
		createPage1();
		
		
		createPage2();
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
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			sortWords();
		}
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
	
	
	void myTests()
	{
		/*MessageDialog.openInformation(
				getSite().getShell(),
				"Hellogroovyworld Plug-in",
				"Hello, Groovy world");*/
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
	}
	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	void sortWords() {

		String editorText =
			editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();

		StringTokenizer tokenizer =
			new StringTokenizer(editorText, " \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList editorWords = new ArrayList();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}
}
