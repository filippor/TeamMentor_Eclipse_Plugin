package tm.eclipse.api;

import static tm.eclipse.api.EclipseLog.*;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.swt.widgets.Composite;

import tm.eclipse.ui.PluginPreferences;
import tm.eclipse.ui.views.DefaultPart_WebBrowser;
import tm.eclipse.ui.views.Eclipse_Panel;

public class Panels  extends EclipseUI
{				
	public Panels(IWorkbench workbench)
	{
		super(workbench);	
	}

	public Eclipse_Panel open_Panel(String panelId)
	{	
		try
		{
			Eclipse_Panel panel = (Eclipse_Panel)activePage().showView(Eclipse_Panel.ID, panelId, IWorkbenchPage.VIEW_ACTIVATE);
			panel.title(panelId);
			return panel;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}		
	}
	
	public Composite refresh(final Composite composite)
	{		
		workbench.getDisplay().asyncExec(new Runnable() { public void run() 
	 		{
				composite.layout();
	        }});
		return composite;
	}
	
	public DefaultPart_WebBrowser open_Url_in_WebBrowser(String browserId, String urlToOpen)
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
		}		
		return null;
	}
	public DefaultPart_WebBrowser open_Html_in_WebBrowser(String htmlToShow)
	{
		return open_Html_in_WebBrowser(PluginPreferences.getDefaultBrowserId(), PluginPreferences.getDefaultBrowserTitle(), htmlToShow);
	}
	public DefaultPart_WebBrowser open_Html_in_WebBrowser(String browserId, String title, String htmlToShow)
	{	
		try 
		{
			
			//IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			IWorkbenchPage activePage = activePage();
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
		}		
		return null;
	}

}
