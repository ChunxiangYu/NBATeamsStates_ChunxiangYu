package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TeamListParser {
    private InputStream in;

    private TeamListParser() throws Exception {
        in = URLCreator.getTeamList();
    }

    public static TeamListParser getNewListOfTeams() throws Exception {
        return new TeamListParser();
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
    public String getTeamId(String teamName) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<String> id = JsonPath.read(document, "$..standard[?(@.fullName=="+ teamName +")].teamId");
        return id.get(0);
    }
    //
    public String getTeamUrlName(String teamId) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<String> urlName = JsonPath.read(document, "$..standard[?(@.teamId=="+ teamId +")].urlName");
        return urlName.get(0);
    }
}
