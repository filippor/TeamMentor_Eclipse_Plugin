/*package g2.groovy.api

import g2.java.api.EclipseAPI;
import org.eclipse.jface.action.*;


class TeamMentorMenu 
{
	public static MenuManager TM_Menu;
	
	public static EclipseAPI eclipseAPI;
	
	public static void createTeamMentorMenu()
	{
		eclipseAPI = new EclipseAPI();
		
		//public void withNewMenu(MenuManager menu)
		//{
		//	eclipseAPI.Add_MenuItem_ShowMessahe(menu,'4rd menu','title','message')
		//}
		
		//eclipseAPI.AddMenu ('TeamMentor', {menu->withNewMenu(menu)});
		
		eclipseAPI.addMenu ('TeamMentor', 
				{ menu -> 
							TM_Menu = menu;
							addDefaultMenuItems();
							
				});		
	}
	
	public static void addDefaultMenuItems()
	{
		eclipseAPI.add_MenuItem_OpenWebPage(TM_Menu,'Open Google','http://google.net');
		eclipseAPI.add_MenuItem_OpenWebPage(TM_Menu,'Open TeamMentor.Net','http://teammentor.net');
		eclipseAPI.add_MenuItem_ShowMessage(TM_Menu,'Say Hi','TeamMentor Message','Hi');		
	} 	
	
}*/
