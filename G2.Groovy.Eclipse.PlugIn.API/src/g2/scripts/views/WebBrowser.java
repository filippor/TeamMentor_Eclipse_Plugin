package g2.scripts.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class WebBrowser extends ViewPart 
{
	public static final String ID = "g2.scripts.views.WebBrowser";
	
	public static String DEFAULT_HTML = "<html>" + 
    									"<link href='http://getbootstrap.com/dist/css/bootstrap.css' rel='stylesheet'>" + 
    									"<h1 class='alert alert-success'>TeamMentor window</h1>... some content will go here....</html>";
	public  Browser webBrowser;
	
	public void createPartControl(Composite parent) 
	{
		//MyEditor myEditor = new MyEditor();
		//myEditor.createPartControl(parent);
		
		//SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
	    //sashForm.setLayout(new RowLayout());
		
	    webBrowser  = new Browser(parent,SWT.BORDER);
	    
	    webBrowser.setText(DEFAULT_HTML);
	    		
	    //browser.webBrowser.setCookie("Session=9e78f231-106b-4f73-a10f-22ab9ebee435","https://teammentor.net");
	    
	    //webBrowser.setUrl("https://teammentor.net/article/7d647e95-e47f-42e3-bb84-fd0dd727245c");
	    //webBrowser.setUrl("http://www.google.com");
	}
	public void setFocus() 
	{
	}
}