package edu.bsu.cs222.model;
import net.minidev.json.JSONArray;
import java.io.InputStream;


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
}
