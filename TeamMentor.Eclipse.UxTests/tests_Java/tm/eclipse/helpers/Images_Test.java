package tm.eclipse.helpers;

import static org.junit.Assert.*;
import java.util.List;
import org.eclipse.swt.graphics.Image;
import org.junit.Test;

public class Images_Test 
{
	public String  expected_FirstName = "IMG_DEC_FIELD_ERROR";
	public String  expected_LastName  = "IMG_OBJS_DND_TOFASTVIEW_SOURCE";
	public int     expected_Size      = 79;
	
	@Test
	public void Images_Ctor()
	{ 
		assertNotNull(images.sharedImages);
	}
	
	@Test
	public void names()
	{ 		
		List<String> names = images.names();
		assertNotNull(names);
		assertTrue   (names.size() > 0);
		assertEquals (names.size()               , expected_Size);
		assertEquals (names.get(0)               , expected_FirstName);
		assertEquals (names.get(expected_Size-1) , expected_LastName);
		
	}
	
	@Test
	public void get()
	{
		Image image_Via_Index = images.get(0);
		Image image_Via_Name = images.get(expected_FirstName);
		assertNotNull(image_Via_Index);
		assertNotNull(image_Via_Name);
		assertEquals (image_Via_Name.getImageData().height, image_Via_Index.getImageData().height);
		assertEquals (image_Via_Name.getImageData().width , image_Via_Index.getImageData().width);
		assertEquals (image_Via_Name, image_Via_Index);
		assertEquals (images.get(expected_Size-1), images.get(expected_LastName));
	}
}