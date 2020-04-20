package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TeamList {
    private InputStream in;

    private TeamList() throws Exception {
        in = URLCreator.getTeamList();
    }

    public static TeamList getNewListOfTeams() throws Exception {
        return new TeamList();
    }

    public List<String> createFullListOfTeams() {

        ArrayList<String> teamList = new ArrayList<>();
        JSONArray teamArray = getTeamList();
        for (Object o : teamArray) {
            teamList.add(o.toString());
        }
        return teamList;
    }

    public JSONArray getTeamList() {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        return JsonPath.read(document, "$..standard[?(@.isNBAFranchise==true)].fullName");
    }

    public List<String> getTeams() throws Exception {
        return getNewListOfTeams().createFullListOfTeams();
    }
}
