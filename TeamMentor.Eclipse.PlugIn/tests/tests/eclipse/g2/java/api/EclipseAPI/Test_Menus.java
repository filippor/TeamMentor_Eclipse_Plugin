package tests.eclipse.g2.java.api.EclipseAPI;

import g2.java.api.EclipseApi.EclipseAPI;
import g2.java.api.EclipseApi.Menus;
import static org.junit.Assert.*;

import org.junit.Test;


public class Test_Menus 
{
	public Menus menus;
		
	public Test_Menus()
	{
		menus = new EclipseAPI().menus;
	}
	
	@Test
	public void getTopMenuManager()
	{
		assertNotNull(menus.getTopMenuManager());
	}
}
