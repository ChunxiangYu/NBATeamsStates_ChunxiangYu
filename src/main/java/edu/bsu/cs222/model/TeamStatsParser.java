package edu.bsu.cs222.model;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.InputStream;
import java.util.List;


public class TeamStatsParser {
    public static final class TeamParserBuilder {
        private InputStream in;
        private String id;

        public TeamParserBuilder(InputStream in)
        {
            this.in = in;
        }

        public TeamStatsParser addId(String id)
        {
            this.id = id;
            return new TeamStatsParser(this);
        }

    }
    public static TeamParserBuilder parseRequest(InputStream in) {
        return new TeamParserBuilder(in);
    }
    private InputStream in;
    private String id;
    private JSONArray jsonArray = new JSONArray();

    public TeamStatsParser(TeamParserBuilder builder)
    {
        this.in = builder.in;
        this.id = builder.id;
    }

    public TeamStats parse() {
        Object json = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].teamId"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].win"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].loss"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].confRank"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].divRank"));
        jsonArray.add(JsonPath.read(json, "$.league.standard.teams[?(@.teamId == '" + id + "')].streak"));
        return TeamStats.build().teamId(((JSONArray) jsonArray.get(0)).get(0).toString()).teamWins(getFloat(1)).teamConfRank(getFloat(3)).teamDivRank(getFloat(4)).teamLoss(getFloat(2));
    }

    public Object parseUrlName(){
        Object json = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<Object> urlName = JsonPath.read(json, "$.league.standard[?(@.teamId == '" + id + "')].urlName");
        return urlName.get(0);
    }
    public Object parseGameData(){
        Object json = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<Object> startData = JsonPath.read(json, "$.league.standard[?(@.gameId == '" + id + "')].startDateEastern");
        return startData.get(0);
    }
    public List<Object> parseGameIdList(){
        Object json = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<Object> timeList = JsonPath.read(json, "$.league..gameId");
        return timeList;
    }
    public List<Object> parseGameDescriptionList(){
        Object json = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<Object> teamList = JsonPath.read(json, "$.plays..formatted..description");
        return teamList;
    }

    private float getFloat(Integer index){
        return Float.parseFloat(((JSONArray) jsonArray.get(index)).get(0).toString());
    }
}
