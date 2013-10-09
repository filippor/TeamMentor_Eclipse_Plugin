package g2.java.api.EclipseApi;

import g2.scripts.views.DefaultPart_WebBrowser;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

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

}
