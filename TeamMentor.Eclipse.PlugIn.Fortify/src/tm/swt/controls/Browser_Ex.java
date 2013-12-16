package tm.swt.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotBrowser;


public class Browser_Ex extends Browser  
{			
	public Display 		 _display;
	public SWTBotBrowser swtBotBrowser;
	
	public Browser_Ex(Composite parent, int style) 
	{				
		super(parent, style);
		swtBotBrowser = new SWTBotBrowser(this);
		_display = parent.getDisplay();
	}
	
	public static Browser_Ex add_Browser(final Composite target)
	{	
		return UIThreadRunnable.syncExec(target.getDisplay(),new Result<Browser_Ex>() { public Browser_Ex run() 
					{
						Browser_Ex browser = new Browser_Ex(target,SWT.None);
						target.layout(true);
						return browser;
					}});
		
	}
	
	//need to overwrite this, or the version in browser.checkSubClass will throw an exception
	protected void checkSubclass()
	{
	
	}
	
	public String getText()
	{			
		// this call to SWTUtils.isUIThread() is not pretty but seems to do the trick 
		// the problem was calling super.setText(text) inside the IThreadRunnable.syncExec
		if(SWTUtils.isUIThread())
			return super.getText();
		return UIThreadRunnable.syncExec(_display,new Result<String>() { public String run() 
									{
										return getText();  		
									}});		
	}
	//Make this call UIThread safe
	public boolean setText(final String text)
	{			
		// this call to SWTUtils.isUIThread() is not pretty but seems to do the trick 
		// the problem was calling super.setText(text) inside the IThreadRunnable.syncExec
		if(SWTUtils.isUIThread())
			return super.setText(text);
		return UIThreadRunnable.syncExec(_display,new Result<Boolean>() { public Boolean run() 
									{
										return setText(text);  		
									}});		
	}

}
