package tests.eclipse.g2.scripts;

import static org.junit.Assert.*;
import g2.scripts.Activator;

import org.junit.Test;

public class Test_Activator {

	@Test
	public void check_that_activator_is_set() 
	{		
		assertNotNull(Activator.plugin);
		assertNotNull(Activator.getDefault());
		assertNull   (Activator.getImageDescriptor("aaa.img"));;
		//assertNotNull(Activator.getImageDescriptor("icons/sample.gif"));;		
		assertEquals (Activator.plugin,Activator.getDefault());
	}		
}
