package edu.bsu.cs222.model;

import java.io.IOException;
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
    public static InputStream getTeamStats(String date) throws Exception {
        date = changeToDate(date);
        URL url = new URL("http://data.nba.net/data/10s/prod/v1/"+date+"/standings_all.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getInputStream();
    }
    public static InputStream getTeamSchedule(String seasonYear, String teamUrlName) throws Exception {
        URL url = new URL("http://data.nba.net/data/10s/prod/v1/"+seasonYear+"/teams/"+teamUrlName+"/schedule.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getInputStream();
    }
    public static InputStream getDescription(String gameId, String gameDate, String period) throws Exception {
        URL url = new URL("http://data.nba.net/data/10s/prod/v1/"+gameDate+"/"+gameId+"_pbp_"+period+".json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getInputStream();
    }

    public static String changeToDate(String year){
        String date = null ;
        switch (year) {
            case "2019":
                date = "current";
                break;
            case "2017":
                date = "20180416";
                break;
            case "2016":
                date = "20170413";
                break;
            case "2015":
                date = "20160413";
                break;
            case "2014":
                date = "20150413";
                break;
        }
        return date;
    }
}
