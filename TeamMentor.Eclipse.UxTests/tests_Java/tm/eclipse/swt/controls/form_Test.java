package tm.eclipse.swt.controls;

import static org.junit.Assert.*;
import org.eclipse.swt.SWTException;
import org.junit.Test;
import tm.swt.controls.Browser_Ex;
import tm.swt.controls.Form_Ex;

public class form_Test 
{
	@Test(expected = SWTException.class)
	public void form_Ctor()
	{
		Form_Ex form = new Form_Ex().layout_Fill();				
		assertNotNull(form);
		assertNotNull(form.shell);
		assertNotNull(form.display);
		assertFalse(form.display.isDisposed());
		assertFalse(form.shell.isDisposed());
		assertFalse(form.shell.isVisible());
		
		form.show();
		assertTrue(form.shell.isVisible());
		assertTrue(form.shell.isEnabled());
		assertFalse(form.display.isDisposed());
		assertFalse(form.shell.isDisposed());
		form.close();

		assertTrue(form.shell.isDisposed());
		assertFalse(form.display.isDisposed());
		assertFalse(form.shell.isVisible());    // should throw SWTException: Widget is disposed
	}
	
	@Test
	public void wait_MiliSeconds()
	{		
		Form_Ex form = new Form_Ex().layout_Fill();	
		Browser_Ex browser = Browser_Ex.add_Browser(form.shell);
		String htmlText = "this is some test <h1>Html Content</h1>";
		
		browser.setText(htmlText);
		
		form.show().wait_MiliSeconds(500);
		assertEquals(browser.getText(),htmlText);		
		form.close();
	}
	
	@Test 
	public void add_Browser()
	{
		Form_Ex form = Form_Ex.popupWindow();
		Browser_Ex browser = form.add_Browser();		
		assertNotNull(browser);
		assertTrue(form.controls().size() > 0);
		assertEquals(browser, form.controls().get(0));		
		form.wait_Seconds(2);
		form.close();
	}
	
}
