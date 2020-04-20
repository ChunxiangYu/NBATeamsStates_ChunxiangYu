package edu.bsu.cs222.model;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLCreator {
    public static InputStream getTeamList() throws Exception {
        URL url = new URL("http://data.nba.net/data/10s/prod/v1/2019/teams.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getInputStream();
    }
}
