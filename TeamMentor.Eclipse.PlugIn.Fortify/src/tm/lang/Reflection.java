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
	
	public Object invoke(String methodName, Object ...parameters )
	{
		/*if(parameters.length == 0)
		{
			Method method = method_Declared(methodName);
			if (method!= null)
				return invoke(method);
		}
		else
		{*/
			Class<?>[] parameterClasses= new Class<?>[parameters.length];
			for(int i=0; i < parameterClasses.length; i++)					//tried to do this with List<Class<?>> but it wasn't working
			{
				Class<?> parameterClass = parameters[i].getClass();		    //this will have probs with boxing of primitive values
				parameterClasses[i] = parameterClass;
			}
			Method method = method_Declared(methodName,parameterClasses);
			if (method!= null)
				return invoke(method,parameters);
		//}
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
	public Object invoke(Method method, Object ...arguments)
	{
		try 
		{
			method.setAccessible(true);	
			return method.invoke(target,arguments);
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
	
	public Method method(String methodName,Class<?> ... parameters )
	{
		return method(clazz, methodName, parameters);
	}
	
	///Recursive Search for method	
	public Method method(Class<?> targetClass, String methodName,Class<?> ... parameters )
	{
		if(targetClass == null)
			return null;
		Method method = this.method_Declared(targetClass, methodName, parameters);
		if(method != null)
			return method;
				
		return method(targetClass.getSuperclass(), methodName, parameters);
	
	}
	public Method method_Declared(String methodName,Class<?> ... parameters)
	{
		return method_Declared(clazz, methodName, parameters);
	}
	public Method method_Declared(Class<?> method_Declared, String methodName,Class<?> ... parameters)
	{
		try  
		{
			return method_Declared.getDeclaredMethod(methodName,parameters);			
		}
		catch (NoSuchMethodException 	 e) 
		{
			//e.printStackTrace(); 
		}
		return null;
	}
	public List<Method> methods()
	{
		return methods(clazz);
	}
	public List<Method> methods(Class<?> targetClass)
	{
		return Arrays.asList(targetClass.getMethods());
	}	
	public List<String> method_Names()
	{
		return method_Names(false);
	}
	public List<String> method_Names(Class<?> targetClass)
	{
		return method_Names(methods(targetClass));
	}
	public List<String> method_Names(boolean onlyShowDeclared)
	{
		return method_Names(onlyShowDeclared ? methods_Declared() : methods());
	}
	public List<String> method_Names(List<Method> methods)
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
