package tm.eclipse.ui.editors;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

//based on code from http://wiki.eclipse.org/FAQ_How_do_I_open_an_editor_on_something_that_is_not_a_file%3F

public class StringStorage implements IStorage 
{
  public String string;
 
  StringStorage(String input) {
    this.string = input;
  }
 
  public InputStream getContents() throws CoreException {
    return new ByteArrayInputStream(string.getBytes());
  }
 
  public IPath getFullPath() {
    return null;
  }
   
public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
    return null;
  }
 
  public String getName() {
    int len = Math.min(5, string.length());
    return string.substring(0, len).concat("..."); //$NON-NLS-1$
  }
 
  public boolean isReadOnly() 
  {	  
    return true;
  }
}