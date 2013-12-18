package tm.eclipse.swt.controls;

import static org.junit.Assert.*;

import java.util.List;

import org.eclipse.swt.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Ignore;
import org.junit.Test;

public class Button_Ex_Test
{
	@Test
	public void text_Ctor()
	{
		Form_Ex form = Form_Ex.popupWindow();
				
		Button_Ex button = Button_Ex.add_Button(form.shell);
		
		assertTrue(form.controls().size() == 1);
		assertEquals(form.controls().get(0), button);
		assertEquals(form.controls().get(0).getClass(), Button_Ex.class);
		form.close();
		assertTrue(form.shell.isDisposed());
		assertTrue(button.isDisposed());
	}
	
	@Test
	public void text_Get_Set()
	{
		Form_Ex form = Form_Ex.popupWindow();
				
		Button_Ex button = form.add_Button();
	
		assertEquals("", button.getText());
		
		String sampleText1 = "123456";		
		
		button.setText(sampleText1);
		assertEquals (sampleText1, button.getText());
		assertTrue (sampleText1.equals(button.getText()));
		
		String sampleText2 = "abcdef";		
		Button_Ex button2 = form.add_Button(sampleText2);
		
		assertEquals (sampleText2, button2.getText());
		assertTrue   (sampleText2.equals(button2.getText()));
		
		form.close();
	}
}
