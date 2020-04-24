package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public void testReadJsonFile() {
        InputStream is = readJsonFile("NBATeamList.json");
        String result = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(result);
    }
    public static InputStream readJsonFile(String filePath){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        return is;
    }

}
