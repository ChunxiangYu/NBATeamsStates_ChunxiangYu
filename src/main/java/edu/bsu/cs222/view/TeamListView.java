package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TeamList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.List;

public class TeamListView extends VBox {
    private final ComboBox<String> teamListBox = new ComboBox<>();

    public TeamListView() throws Exception {
        VBox teamListBox = createTeamListBox();
        getChildren().add(teamListBox);
    }

    private VBox createTeamListBox() throws Exception {
        teamListBox.setPromptText("Select a team");
        teamListBox.setItems(FXCollections.observableList(getTeamList()));
        return new VBox(teamListBox);
    }
    private List<String> getTeamList() throws Exception {
        TeamList teamList = TeamList.getNewListOfTeams();
        return teamList.createFullListOfTeams();
    }

}
