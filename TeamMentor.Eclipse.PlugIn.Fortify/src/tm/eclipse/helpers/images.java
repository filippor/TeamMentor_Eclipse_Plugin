package tm.eclipse.helpers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class images 
{
	public static ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
	
	public static List<String> list()
	{
		return names();
	}
	public static List<String> names()
	{		
		List<String> names = new ArrayList<String>();
		for(Field field : sharedImages.getClass().getFields())
			if(field.getName().contains("MASK") == false)
				names.add(field.getName());
		return names;
	}

	public static Image get(int index)
	{		
		List<String> names = names();
		if(names.size() > index)
			return  sharedImages.getImage(names.get(index));
		return null;
	}
	public static Image get(String symbolicName)
	{		
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		return sharedImages.getImage(symbolicName);
	}
}
