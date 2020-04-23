package edu.bsu.cs222.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeamMatches {
    public String getTeamUrlName(String teamId) throws Exception {
        return TeamListParser.getNewListOfTeams().getTeamUrlName(teamId);
    }

    public static List findMatch(List<Object> list1, List<Object> list2)throws Exception {
        Collection exists=new ArrayList(list2);
        Collection notExists=new ArrayList(list2);
        exists.removeAll(list1);
        notExists.removeAll(exists);

        return (List) notExists;
    }

    public static String getGameData(String seasonYear, String compareTeamUrlName, String gameId) throws Exception{
        String gameData = (String) TeamStatsParser.parseRequest(URLCreator.getTeamSchedule(seasonYear, compareTeamUrlName)).addId(gameId).parseGameData();
        return gameData;
    }

}
