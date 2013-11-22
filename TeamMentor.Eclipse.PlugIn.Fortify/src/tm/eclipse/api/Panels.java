package tm.eclipse.api;

import static tm.eclipse.api.EclipseLog.*;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.swt.widgets.Composite;

import tm.eclipse.ui.PluginPreferences;
import tm.eclipse.ui.views.DefaultPart_WebBrowser;
import tm.eclipse.ui.views.Eclipse_Panel;

public class Panels  extends EclipseUI
{		
	Eclipse_Panel panel;
	
	public Panels(IWorkbench workbench)
	{
		super(workbench);	
	}

	public Eclipse_Panel open_Panel(final String panelId)
	{	
		return syncExec(new Result<Eclipse_Panel>() { public Eclipse_Panel run() 
			{				
				try 
				{
					Eclipse_Panel panel = (Eclipse_Panel)activePage.showView(Eclipse_Panel.ID, panelId, IWorkbenchPage.VIEW_ACTIVATE);
					panel.title(panelId);
					return panel;
				} catch (PartInitException e) 
				{
					e.printStackTrace();
					return null;
				}				
			}});
	}
	
	public Composite refresh(final Composite composite)
	{				
		//workbench.getDisplay().asyncExec(new Runnable() { public void run() 
		return syncExec(new Result<Composite>() { public Composite run()
	 		{
				composite.layout();
				return composite; 
	        }});
		
	}
	
	public DefaultPart_WebBrowser open_Url_in_WebBrowser(final String browserId, final String urlToOpen)
	{	
		return syncExec(new Result<DefaultPart_WebBrowser>() { public DefaultPart_WebBrowser run() 
			{
				try 
				{
					IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
					DefaultPart_WebBrowser webBrowserPart = (DefaultPart_WebBrowser)page.showView(DefaultPart_WebBrowser.ID, browserId, IWorkbenchPage.VIEW_ACTIVATE);
					webBrowserPart.browser.setUrl(urlToOpen);
					webBrowserPart.setName(urlToOpen);
					return webBrowserPart;			
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					return null;
				}		
			}});
	}
	public DefaultPart_WebBrowser open_Html_in_WebBrowser(String htmlToShow)
	{
		return open_Html_in_WebBrowser(PluginPreferences.getDefaultBrowserId(), PluginPreferences.getDefaultBrowserTitle(), htmlToShow);
	}
	public DefaultPart_WebBrowser open_Html_in_WebBrowser(final String browserId, final String title, final String htmlToShow)
	{	
		return syncExec(new Result<DefaultPart_WebBrowser>() { public DefaultPart_WebBrowser run() 
			{
				try 
				{	
					if (activePage == null)				
					{
						log_Error("in open_Html_in_WebBrowser, activePage was null, which usually means that the caller was not on the UI thread");
						return null;
					}
					IViewPart viewPart = activePage.showView(DefaultPart_WebBrowser.ID, browserId, IWorkbenchPage.VIEW_ACTIVATE);
					
					DefaultPart_WebBrowser webBrowserPart = (DefaultPart_WebBrowser)viewPart;		
					webBrowserPart.setName(title);			
					webBrowserPart.browser.setText(htmlToShow);
					return webBrowserPart;						
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					return null;
				}				
			}});		
	}

}
