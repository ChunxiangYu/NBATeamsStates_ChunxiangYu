package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamStatsParserTest {
    @Test
    public void testParse() throws Exception {
        TeamStats teamStats = TeamStatsParser.parseRequest(URLCreator.getTeamStats("2019")).addId(TeamStats.getTeamId("Brooklyn Nets")).parse();
        String realStats = " Team ID: 1610612751\n" +
                " Wins: 30.0\n" +
                " Losses: 34.0\n" +
                " Conference Rank: 7.0\n" +
                " Division Rank: 4.0";
        boolean b = realStats.equals(String.valueOf(teamStats));
        assertTrue(b);

    }
}
