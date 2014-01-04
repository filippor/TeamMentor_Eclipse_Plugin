package tm.utils;

public class Consts_Eclipse 
{
	public static String VIEW_ID_WELCOME_SCREEN = "org.eclipse.ui.internal.introview";	
	public static String DEFAULT_TM_NET_OFFLINE_MESSAGE = "Could not open https://teammentor.net because it looks like we are offline at the moment";
	
	public static String TEST     = "12345";
	public static String TEST_NEW = "abcdef";
	public static String TEST_NEW_AGAIN = "It's 42";

	public static String newStaticMethod()
	{
		return new Consts_Eclipse().newMethod();
	}
	public String newMethod()
	{
		return "from a new Method";
	}
}


//return tm.utils.Consts_Eclipse.TEST