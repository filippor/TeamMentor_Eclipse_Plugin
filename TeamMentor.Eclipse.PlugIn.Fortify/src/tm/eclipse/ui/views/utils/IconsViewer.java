package tm.eclipse.ui.views.utils;

import tm.eclipse.api.EclipseAPI;
import tm.eclipse.helpers.Images;
import tm.eclipse.swt.controls.Composite;
import tm.eclipse.swt.controls.Label;
import tm.eclipse.swt.controls.Text;
import tm.eclipse.ui.Startup;
import tm.eclipse.ui.views.Eclipse_Panel;

public class IconsViewer 
{
	EclipseAPI    eclipse; 
	Eclipse_Panel view;
	Text 		  text;
	Composite     panel;
	
	public IconsViewer()
	{
		eclipse = Startup.eclipseApi;
		createGui();
	}
	public IconsViewer createGui()
	{
		view = eclipse.views.create("Images Embeded in Eclipse");
		view.clear().set.layout.grid(2)		
			.add.label("Hovered image:");
		
		text = view.add.text()
				   .set.layout.grid_Grab_Horizontal();

		panel = view.add.panel()
					.set.layout.row()
					.set.layout.grid_Grab_All(2,1);
		for(String name : Images.names())
		{
				final Label image = panel.add.image(Images.get(name),name);
	
				image.set.event.onMouseEnter(new java.lang.Runnable() { public void run() 
							{
								text.text(image.get.toolTip());
							}});
				}
		view.refresh();			
		return this;
	}
}
