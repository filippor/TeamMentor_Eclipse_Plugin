package tm.eclipse.api;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;

import tm.eclipse.api.JavaCompilationUnit;

public class Editors 
{
	public EclipseAPI eclipse;
	
	public Editors(EclipseAPI eclipse)
	{
		this.eclipse = eclipse;
	}
		
	public IEditorPart active()
	{				
		return eclipse.activeWorkbenchPage.getActiveEditor();
	}	
	public JavaCompilationUnit active_JavaCompilationUnit()
	{				
		return javaCompilationUnit(active());
	}
	
	public IEditorReference get(String title)
	{
		return reference(title);
	}
			
	public List<String>           ids()
	{
		List<String> ids = new ArrayList<String>();
		for(IEditorReference view : this.list())
			ids.add(view.getId());
		return  ids;
	}
	public JavaCompilationUnit javaCompilationUnit(IEditorPart editorPart)
	{
		return JavaCompilationUnit.newFromEditorPart(editorPart);
	}
	public JavaCompilationUnit javaCompilationUnit(String methodName)
	{
		for(JavaCompilationUnit javaCompilationUnit : javaCompilationUnits())
			if(javaCompilationUnit.name().equals(methodName))
					return javaCompilationUnit;
		return null;
	}
	public List<JavaCompilationUnit> javaCompilationUnits()
	{
		return JavaCompilationUnit.newFromEditorReferences(Editors.this.list());
	}
	public List<IEditorReference> list()
	{
		return references();
	}
	public List<IEditorReference> open()
	{
		return references();
	}
	public List<String>           partNames()
	{
		List<String> ids = new ArrayList<String>();
		for(IEditorReference view : this.list())
			ids.add(view.getPartName());
		return  ids;
	}
	public IEditorReference 		 reference(String title)
	{
		for(IEditorReference editor : references())
			if(editor.getTitle().equals(title))
					return editor;
		return null;
	}
	
	public List<IEditorReference> references()
	{
		return syncExec(new Result<List<IEditorReference>>() { public List<IEditorReference> run ()  
			{
				return Arrays.asList(eclipse.activeWorkbenchPage.getEditorReferences());
			}});
	}
	public IEditorPart show(final IEditorPart editorPart)
	{
		return syncExec(new Result<IEditorPart>() { public IEditorPart run ()  
			{				
				eclipse.activeWorkbenchPage.activate(editorPart);
				return editorPart;
			}});
	}
	
	public IEditorPart show(final IEditorReference editorReference)
	{
		return syncExec(new Result<IEditorPart>() { public IEditorPart run ()  
			{
				IEditorPart editorPart = editorReference.getEditor(true);
				return show(editorPart);
			}});
	}
	
	public List<String>           titles()
	{
		List<String> titles = new ArrayList<String>();
		for(IEditorReference view : this.list())
			titles.add(view.getTitle());
		return  titles;
	}	
}