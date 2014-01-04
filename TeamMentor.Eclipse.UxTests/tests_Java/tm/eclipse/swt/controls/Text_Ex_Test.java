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

public class Text_Ex_Test
{
	@Test
	public void text_Ctor()
	{
		Form_Ex form = Form_Ex.popupWindow();
				
		Text text = Text.add_Text(form.shell);
		
		assertTrue(form.controls().size() == 1);
		assertEquals(form.controls().get(0), text);
		form.close();
		assertTrue(form.shell.isDisposed());
		assertTrue(text.isDisposed());
	}
	
	@Test
	public void text_Get_Set()
	{
		Form_Ex form = Form_Ex.popupWindow();
				
		Text text = form.add_Text();
		assertEquals("", text.getText());
		
		String sampleText1 = "123456";
		String sampleText2 = "abcdef";
		
		text.setText(sampleText1);
		assertEquals (sampleText1, text.getText());
		assertTrue (sampleText1.equals(text.getText()));
		assertFalse (sampleText2.equals(text.getText()));
		
		text.setText(sampleText2);
		assertFalse (sampleText1.equals(text.getText()));
		assertTrue (sampleText2.equals(text.getText()));
		
		text.setText(sampleText1 + sampleText2);
		assertFalse (sampleText1.equals(text.getText()));
		assertFalse (sampleText2.equals(text.getText()));
		assertTrue ((sampleText1 + sampleText2).equals(text.getText()));
		form.close();
	}
	
	@Test
	public void text_Search()
	{
		Form_Ex form = Form_Ex.popupWindow();
		Text text = form.add_Text_Search();
		assertNotNull(text);
		assertEquals(1,form.controls().size());
		Text.add_Text_Search(form.shell);
		assertEquals(2,form.controls().size());
		assertEquals(Text.class,form.controls().get(0).getClass());
		assertEquals(Text.class,form.controls().get(1).getClass());
		
	}
}
