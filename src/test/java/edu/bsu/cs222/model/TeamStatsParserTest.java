package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamStatsParserTest {
    @Test
    public void testParse() throws Exception {
        TeamStats teamStats = TeamStatsParser.parseRequest(URLCreatorTest.readJsonFile("standings_all.json")).addId(TeamStats.getTeamId("Brooklyn Nets")).parse();
        String realStats = " Team ID: 1610612751\n" +
                " Wins: 30.0\n" +
                " Losses: 34.0\n" +
                " Conference Rank: 7.0\n" +
                " Division Rank: 4.0";
        boolean b = realStats.equals(String.valueOf(teamStats));
        assertTrue(b);
    }
    @Test
    public void testParseUrlName() throws Exception {
        String teamUrlName = (String) TeamStatsParser.parseRequest(URLCreatorTest.readJsonFile("NBATeamList.json")).addId(TeamStats.getTeamId("Brooklyn Nets")).parseUrlName();
        String realUrlName = "nets";
        boolean b = realUrlName.equals(String.valueOf(teamUrlName));
        assertTrue(b);
    }
    @Test
    public void testParseGameIdList() throws Exception {
        List<Object> gameId =  TeamStatsParser.parseRequest(URLCreatorTest.readJsonFile("hawksSchedule.json")).addId(TeamStats.getTeamId("Brooklyn Nets")).parseGameIdList();
        String realId = "0011900015";
        boolean b = realId.equals(String.valueOf(gameId.get(0)));
        assertTrue(b);
    }
    @Test
    public void testParseGameData()throws Exception{
        String gameData = getGameData( "0011900015");
        String realData = "20191007";
        boolean b = realData.equals(String.valueOf(gameData));
        assertTrue(b);
    }

    public String getGameData( String gameId){
        String gameData = (String) TeamStatsParser.parseRequest(URLCreatorTest.readJsonFile("hawksSchedule.json")).addId(gameId).parseGameData();
        return gameData;
    }

}
