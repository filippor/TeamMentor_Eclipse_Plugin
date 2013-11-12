package tm.eclipse.ui.views;

import static org.eclipse.swtbot.swt.finder.SWTBotAssert.assertContains;
import static org.junit.Assert.*;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotStyledText;
import org.junit.Test;

import tm.swtbot.SWTBot_JUnit;

public class SimpleEditor_Test extends SWTBot_JUnit
{
	public final String VIEW_ID = "g2.scripts.views.SimpleEditor";
	
	@Test
	public void SimpleEditor_Open()
	{
		SWTBotView swtBotView = views.get_View_Fast(VIEW_ID);
		if(swtBotView != null)
			swtBotView.close();
		
		swtBotView = views.open_View(VIEW_ID);
		assertNotNull(swtBotView);		
		
		//get StyledText (with code and result)
		swtBotView.setFocus();
		SWTBotStyledText code_StyledText   =  bot.styledText(0);
		SWTBotStyledText result_StyledText =  bot.styledText(1);
		assertNotNull(code_StyledText);
		assertNotNull(code_StyledText.getText());
		assertNotNull(result_StyledText);		
		assertNotNull(result_StyledText.getText());
		assertContains("return eclipseAPI;",code_StyledText.getText());
		assertEquals  (""   			   ,result_StyledText.getText());
		
		//set test Text 
		String groovyScript    = "40 + 2";
		String expectedResult  = "42";
		code_StyledText.setText(groovyScript);
		assertEquals           (groovyScript,code_StyledText.getText());
		
		//click on button and check result
		SWTBotButton button =  bot.button();
		assertNotNull(button);
		assertNotNull(button.click());
		assertEquals (expectedResult,result_StyledText.getText());

		//one more test
		code_StyledText.setText("return eclipseAPI;");
		button.click();
		assertContains("tm.eclipse.api.EclipseAPI",result_StyledText.getText());
	}
}
