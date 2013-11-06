package g2.java.api;

import static org.junit.Assert.*;

import org.junit.Test;

import tm.eclipse.api.TeamMentorAPI;

public class TeamMentorAPI_Test 
{
	
	TeamMentorAPI teamMentorApi;
	
	public TeamMentorAPI_Test()
	{
		teamMentorApi = new TeamMentorAPI();
	}
	
	@Test
	public void TeamMentorAPI_Ctor()
	{
		assertNotNull(teamMentorApi);
		assertNotNull(TeamMentorAPI.eclipseAPI);		
	}	
}
