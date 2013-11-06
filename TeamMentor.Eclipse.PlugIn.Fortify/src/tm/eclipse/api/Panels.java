package tm.eclipse.api;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

import tm.eclipse.ui.PluginPreferences;
import tm.eclipse.ui.views.DefaultPart_WebBrowser;

public class Panels 
{
	public IWorkbench 		workbench;
	
	public Panels(IWorkbench _workbench)
	{
		workbench = _workbench;
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
			IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
			DefaultPart_WebBrowser webBrowserPart = (DefaultPart_WebBrowser)page.showView(DefaultPart_WebBrowser.ID, browserId, IWorkbenchPage.VIEW_ACTIVATE);			
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
