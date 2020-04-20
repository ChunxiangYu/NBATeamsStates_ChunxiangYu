package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLCreatorTest {
    @Test
    public void testGetTeamListUrlStream() throws Exception {
        InputStream url = URLCreator.getTeamList();
        Assertions.assertNotNull(url);
    }
    @Test
    public void testGetTeamListUrl() throws Exception {
        InputStream url = URLCreator.getTeamList();
        boolean b = "http://data.nba.net/data/10s/prod/v1/2018/teams.json".compareTo(String.valueOf(url)) != 0;
        Assertions.assertTrue(b);
    }
}
