package edu.bsu.cs222.view;
import edu.bsu.cs222.model.*;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ControllerPane extends VBox {

    private String seasonYear;
    private String teamStats;
    private String compareTeamStats;
    private String teamName;
    private String gameData;
    private List<Object> gameDescription;
    private List<Object> teamGameId;
    private List<Object> compareTeamGameId;
    private Object teamUrlName;
    private Object compareTeamUrlName;
    private final Button getMatchList = new Button();
    private final Label statsLabel = new Label();
    private final Label compareStatsLabel = new Label();
    private final TextArea matchDescription = new TextArea();
    private final ComboBox<String> yearListBox = new ComboBox<>();
    private final ComboBox<String> teamListBox = new ComboBox<>();
    private final ComboBox<String> compareTeamListBox = new ComboBox<>();
    private final ComboBox<String> matchesListBox = new ComboBox<>();
    private final ComboBox<String> period = new ComboBox<>();
    private Map<String,String> idData=new HashMap<>();


    public VBox yearListComboBox(){
        return createYearListBox();
    }
    public VBox teamListComboBox() throws Exception {
        return createTeamListBox();
    }
    public VBox compareTeamListComboBox() throws Exception {
        return createCompareTeamListBox();
    }

    public VBox matchesListComboBox() {
        return createMatchesListBox();
    }

    private VBox createYearListBox() {
        yearListBox.setPromptText("Select a year");
        yearListBox.setItems(FXCollections.observableArrayList(
                "2019", "2017","2016","2015")
        );
        yearListBox.getSelectionModel().selectedItemProperty().addListener((ov, t, t1) -> seasonYear = yearListBox.getValue());
        return new VBox(yearListBox);
    }

    private VBox createTeamListBox() throws Exception {
        teamListBox.setPromptText("Select a team");
        teamListBox.setItems(FXCollections.observableList(getTeamList()));
        teamListBox.valueProperty().addListener((ov, t, t1) -> {
            try {
                teamStats = String.valueOf(TeamStatsParser.parseRequest(URLCreator.getTeamStats(seasonYear)).addId(TeamStats.getTeamId(t1)).parse());
                statsLabel.setText(teamStats);
                teamUrlName = TeamStatsParser.parseRequest(URLCreator.getTeamList()).addId(TeamStats.getTeamId(t1)).parseUrlName();
                teamGameId = TeamStatsParser.parseRequest(URLCreator.getTeamSchedule(seasonYear,(String)teamUrlName)).addId(TeamStats.getTeamId(t1)).parseGameIdList();
               // teamGameData = TeamStatsParser.parseRequest(URLCreator.getTeamSchedule(seasonYear,(String)teamUrlName)).addId(TeamStats.getTeamId(t1)).parseGameDataList();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new VBox(teamListBox,statsLabel);
    }

    private VBox createCompareTeamListBox() throws Exception {
        compareTeamListBox.setPromptText("Select a team");
        compareTeamListBox.setItems(FXCollections.observableList(getTeamList()));
        compareTeamListBox.valueProperty().addListener((ov, t, t1) -> {
            try {
                compareTeamStats = String.valueOf(TeamStatsParser.parseRequest(URLCreator.getTeamStats(seasonYear)).addId(TeamStats.getTeamId(t1)).parse());
                compareStatsLabel.setText(compareTeamStats);
                teamName = t1;
                compareTeamUrlName = TeamStatsParser.parseRequest(URLCreator.getTeamList()).addId(TeamStats.getTeamId(t1)).parseUrlName();
                compareTeamGameId = TeamStatsParser.parseRequest(URLCreator.getTeamSchedule(seasonYear,(String)compareTeamUrlName)).addId(TeamStats.getTeamId(t1)).parseGameIdList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new VBox(compareTeamListBox,compareStatsLabel);
    }

    private VBox createMatchesListBox(){
        matchDescription.setText("");
        getMatchList.setText("Click to see the matches list");
        matchesListBox.setPromptText("Select a Match");
        period.setPromptText("Select a period");
        period.setItems(FXCollections.observableArrayList(
                "1","2","3","4")
        );
        getMatchList.setOnAction(actionEvent -> {

            try {
                matchDescription.setText("");
                String matchGameId1 = (String) TeamMatches.findMatch(teamGameId,compareTeamGameId).get(0);
                String matchGameId2 = (String) TeamMatches.findMatch(teamGameId,compareTeamGameId).get(1);
                String matchGameId3 = (String) TeamMatches.findMatch(teamGameId,compareTeamGameId).get(2);
                //String matchGameId4 = (String) TeamMatches.findMatch(teamGameId,compareTeamGameId).get(3);
                String teamGameData1 = TeamMatches.getGameData(seasonYear, (String) compareTeamUrlName,matchGameId1);
                String teamGameData2 = TeamMatches.getGameData(seasonYear, (String) compareTeamUrlName,matchGameId2);
                String teamGameData3 = TeamMatches.getGameData(seasonYear, (String) compareTeamUrlName,matchGameId3);
                idData.put(teamGameData1,matchGameId1);
                idData.put(teamGameData2,matchGameId2);
                idData.put(teamGameData3,matchGameId3);

                matchesListBox.setItems(FXCollections.observableArrayList(
                        teamGameData1,teamGameData2,teamGameData3)
                );
                matchesListBox.valueProperty().addListener((ov, t, t1) -> {
                    try {
                        gameData = matchesListBox.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                period.valueProperty().addListener((ov, t, t1) -> {
                    try {
                       // matchDescription.clear();
                        gameDescription = TeamStatsParser.parseRequest(URLCreator.getDescription(idData.get(gameData),gameData,t1)).addId(TeamStats.getTeamId(teamName)).parseGameDescriptionList();
                        String[] description = String.valueOf(gameDescription).split(",");
                        for (String s : description) {
                            matchDescription.appendText(s + "\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
               } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return new VBox(getMatchList,matchesListBox,period,matchDescription);
    }

























    private List<String> getTeamList() throws Exception {
        TeamListParser teamListParser = TeamListParser.getNewListOfTeams();
        return teamListParser.createFullListOfTeams();
    }



}
