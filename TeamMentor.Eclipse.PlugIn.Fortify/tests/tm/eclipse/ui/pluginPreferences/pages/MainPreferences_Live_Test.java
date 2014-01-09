package tm.eclipse.ui.pluginPreferences.pages;

import static org.junit.Assert.*;
import groovy.inspect.swingui.ObjectBrowser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IViewPart;
import org.junit.Test;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.helpers.colors;
import tm.eclipse.swt.Mouse;
import tm.eclipse.swt.controls.Text;
import tm.eclipse.ui.Startup;
import tm.eclipse.ui.views.Eclipse_Panel;


public class MainPreferences_Live_Test 
{
	public static MainPreferences propertyPage;
	EclipseAPI 	    eclipse ;
	
	Eclipse_Panel   view;
	Mouse 			mouse;
	
	public MainPreferences_Live_Test()
	{
		eclipse = Startup.eclipseApi;
		mouse = eclipse.mouse;		
		view = null;
	}
	
	
	@Test
	public void openPropertiesWindow()
	{
		propertyPage = new MainPreferences();
		String viewId =  "Test main TM Propeties Window";
		view = eclipse.views.create(viewId);
		view.close();
		
		assertTrue(view.composite.isDisposed());
		view = eclipse.views.create(viewId);
		eclipse.syncExec( new Runnable() {  public void run() 
			{			
				view.composite.setBackground(colors.get(SWT.COLOR_WIDGET_BACKGROUND));
				propertyPage.setControl(view.composite);
				propertyPage.createContents(view.composite);
			}});
		view.refresh();
		assertNotNull(view);
		assertFalse(view.composite.isDisposed());
		eclipse.log("propertyPage: " + propertyPage.password_Label);
	}
	@Test
	public void moveMouse()
	{
		//openPropertiesWindow();
		//view.composite.layout(true);
		//final Text text = propertyPage.password_Text;
		
		/*Thread thread = new Thread(new Runnable() { public void run()
			{eclipse.log("inthread");
				eclipse.log(text.getLocation().toString());	
			}});
		thread.start();
		try {
			thread.join();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		eclipse.log("Location: " + propertyPage);
		mouse.move(propertyPage.password_Text);
		eclipse.syncExec( new Runnable() {  public void run()
			{
			eclipse.log("Location: ");
			
			
			Eclipse_Panel view2 = (Eclipse_Panel)eclipse.views.get("Test main TM Propeties Window").getView(false);
			//return view;
				Point location = ((Control)((Composite)((Composite)view2.composite
									 .getChildren()[0])
									 .getChildren()[1])
									 .getChildren()[1]).getLocation();
				eclipse.log("Location: " + location.toString());
				//eclipse.log(propertyPage.password_Text.toControl(0,0).toString());
			    //eclipse.log(propertyPage.p.toControl(0,0).toString());
			    //propertyPage.password_Text.setText("123123221312");
				
			}});
		
	}
	
	@Test
	public void anotherOne()
	{
		
	}
	@Test
	public void oneMore4()
	{		
		assertTrue(1==1);	
	}
}
