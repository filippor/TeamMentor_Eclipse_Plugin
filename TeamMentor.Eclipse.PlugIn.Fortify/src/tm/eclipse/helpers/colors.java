package tm.eclipse.helpers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class colors
{
	public static Color get(int systemColorId)
	{
		return Display.getCurrent ().getSystemColor(systemColorId);
	}
	public static Color black()
	{
		return get(SWT.COLOR_BLACK);
	}
	public static Color blue()
	{
		return get(SWT.COLOR_BLUE);
	}
	public static Color green()
	{
		return get(SWT.COLOR_GREEN);
	}
	public static Color red()
	{
		return get(SWT.COLOR_RED);
	}
	public static Color white()
	{
		return get(SWT.COLOR_WHITE);
	}
}
