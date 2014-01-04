package tm.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	
}
