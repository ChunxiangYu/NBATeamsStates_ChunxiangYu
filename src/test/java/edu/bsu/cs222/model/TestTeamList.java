package edu.bsu.cs222.model;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class TestTeamList {

    @Test
    public void testCreateFullListOfTeams() throws Exception {
        List<String> list = TeamList.getNewListOfTeams().createFullListOfTeams();
        Assertions.assertNotNull(list);
    }
    @Test
    public void testGetTeamList() throws Exception {
        TeamList listOfTeams = TeamList.getNewListOfTeams();
        JSONArray teams = listOfTeams.getTeamList();
        Assertions.assertNotNull(teams);
    }

}
