package edu.bsu.cs222.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamStatsTest {
    @Test
    public void testGetTeamId() throws Exception {
        String teamId = TeamStats.getTeamId("Brooklyn Nets");
        boolean b = "1610612751".equals(teamId);
        assertTrue(b);
    }
}
