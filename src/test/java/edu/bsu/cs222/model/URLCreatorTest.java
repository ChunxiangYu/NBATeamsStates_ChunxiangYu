package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(b);
    }
    @Test
    public void testGetTeamStatsUrlStream() throws Exception {
        InputStream url = URLCreator.getTeamStats("2019");
        Assertions.assertNotNull(url);
    }
    @Test
    public void testGetTeamStatsUrl() throws Exception {
        InputStream teamStatsUrl = URLCreator.getTeamStats("2019");
        InputStream teamListStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("standings_all.json");
        byte[] bytes;
        bytes = new byte[teamStatsUrl.available()];
        System.out.println(bytes.length);
        teamStatsUrl.read(bytes);
        String str = new String(bytes);
        System.out.println(str);
        boolean b = str.equals(teamStatsUrl);
        assertTrue(b);
    }
}
