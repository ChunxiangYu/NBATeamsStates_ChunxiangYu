package edu.bsu.cs222.model;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamListParserTest {

    @Test
    public void testReadJsonFile(){
        InputStream result = URLCreatorTest.readJsonFile("NBATeamList.json");
        Assertions.assertNotNull(result);
    }
    @Test
    public void testTeamListRight(){
        JSONArray teamArray = getTeamList(URLCreatorTest.readJsonFile("NBATeamList.json"));
        String teamName = (String) teamArray.get(1);
        boolean b = "Boston Celtics".equals(teamName);
        assertTrue(b);

    }
    @Test
    public void testTeamIdRight(){
        String teamId = getTeamId(URLCreatorTest.readJsonFile("NBATeamList.json"),"Atlanta Hawks");
        boolean b = "1610612737".equals(teamId);
        assertTrue(b);
    }

    public static JSONArray getTeamList(InputStream in) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        return JsonPath.read(document, "$..standard[?(@.isNBAFranchise==true)].fullName");
    }
    public String getTeamId(InputStream in,String teamName) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<String> id = JsonPath.read(document, "$..standard[?(@.fullName=="+ teamName +")].teamId");
        return id.get(0);
    }


}
