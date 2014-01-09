package tm.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflection 
{
	public Object target;
	public Class<?>  clazz;
	
	public Reflection(Object target)
	{
		this.target = target;
		this.clazz = target.getClass();
	}
	
	public Object invoke(String methodName)
	{
		try  
		{
			Method method = clazz.getDeclaredMethod("click");
			return invoke(method);
		}
		catch (NoSuchMethodException 	 e) { e.printStackTrace(); }
		return null;
	}
	public Object invoke_onSuperClass(String methodName)
	{
		try
		{
			Method method = clazz.getSuperclass().getDeclaredMethod(methodName);
			return invoke(method);
		}
		catch (NoSuchMethodException 	 e) { e.printStackTrace(); }
		return null;
	}
	public Object invoke(Method method)
	{
		try 
		{
			method.setAccessible(true);	
			return method.invoke(target);
		} 
		catch (SecurityException 		 e) { e.printStackTrace(); }		
		catch (IllegalArgumentException  e) { e.printStackTrace(); }
		catch (IllegalAccessException 	 e) { e.printStackTrace(); }
		catch (InvocationTargetException e) { e.printStackTrace(); }
		return null;
	}
	
	public static Reflection reflection(Object target)
	{
		Reflection reflection = new Reflection(target);		
		return reflection;
	}
	
	public List<Method> methods()
	{
		return Arrays.asList(clazz.getMethods());
	}	
	public List<String> methods_Names()
	{
		return methods_Names(false);
	}
	public List<String> methods_Names(boolean onlyShowDeclared)
	{
		return methods_Names(onlyShowDeclared ? methods_Declared() : methods());
	}
	public List<String> methods_Names(List<Method> methods)
	{
		List<String> names = new ArrayList<String>();
		for(Method method : methods)			
			names.add(method.getName());
		return names;
	}
	public List<Method> methods_Declared()
	{
		return Arrays.asList(clazz.getDeclaredMethods());
	}
	public Reflection inspect()
	{
		groovy.inspect.swingui.ObjectBrowser.inspect(target);
		return this;
	}
}
