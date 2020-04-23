package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamListParserTest {

    @Test
    public void testCreateFullListOfTeams() throws Exception {
        List<String> list = TeamListParser.getNewListOfTeams().createFullListOfTeams();
        Assertions.assertNotNull(list);
    }
    @Test
    public void testGetTeamList() throws Exception {
        TeamListParser listOfTeams = TeamListParser.getNewListOfTeams();
        JSONArray teams = listOfTeams.getTeamList();
        Assertions.assertNotNull(teams);
    }
    @Test
    public void testTeamListRight() throws Exception{
        JSONArray teamArray = TeamListParser.getNewListOfTeams().getTeamList();
        String teamName = (String) teamArray.get(1);
        boolean b = "Boston Celtics".equals(teamName);
        assertTrue(b);
    }
    @Test
    public void testTeamIdRight() throws Exception{
        String teamId = TeamListParser.getNewListOfTeams().getTeamId("Atlanta Hawks");
        boolean b = "1610612737".equals(teamId);
        assertTrue(b);
    }

}
