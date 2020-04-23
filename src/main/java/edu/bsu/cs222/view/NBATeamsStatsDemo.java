package edu.bsu.cs222.view;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NBATeamsStatsDemo extends Application{
    ControllerPane controllerPane = new ControllerPane();
    //TeamStatsView teamStatsView = new TeamStatsView();
    public Label titleLabel;
    public VBox listOfTeams;
    public VBox listOfCompareTeams;
    public VBox listOfYears;
    public VBox listOfMatches;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Final Project");
        BorderPane layout = createLayout();
        primaryStage.setScene(new Scene(layout, 1000, 500));
        primaryStage.show();
    }

    private BorderPane createLayout() throws Exception {
        BorderPane borderPane = new BorderPane();
        VBox yearBox = buildYearBox();
        VBox compareTeamListBox = buildCompareTeamListBox();
        VBox teamListBox = buildTeamListBox();
        VBox matchesBox = buildMatchesBox();
        yearBox.setSpacing(10);
        yearBox.alignmentProperty();
        teamListBox.setAlignment(Pos.TOP_CENTER);
        teamListBox.setSpacing(10);
        compareTeamListBox.setAlignment(Pos.TOP_CENTER);
        compareTeamListBox.setSpacing(10);
        matchesBox.setAlignment(Pos.CENTER);
        matchesBox.setSpacing(10);
        borderPane.setLeft(yearBox);
        borderPane.setCenter(teamListBox);
        borderPane.setRight(compareTeamListBox);
        borderPane.setBottom(matchesBox);
        return borderPane;
    }
    public VBox buildYearBox(){
        titleLabel = new Label("First, select a year:");
        titleLabel.setFont(new Font(18));
        listOfYears = controllerPane.yearListComboBox();
        return new VBox(titleLabel,listOfYears);
    }

    public VBox buildTeamListBox() throws Exception {
        titleLabel = new Label("Second, select a team to view the stats:");
        titleLabel.setFont(new Font(18));
        listOfTeams = controllerPane.teamListComboBox();
        return new VBox(titleLabel,listOfTeams);
    }

    public VBox buildCompareTeamListBox()throws Exception {
        titleLabel = new Label("Third, compare with another team:");
        titleLabel.setFont(new Font(18));
        listOfCompareTeams = controllerPane.compareTeamListComboBox();
        return new VBox(titleLabel,listOfCompareTeams);
    }

    public VBox buildMatchesBox() throws Exception{
        titleLabel = new Label("Forth, click the button and select a match");
        titleLabel.setFont(new Font(18));
        listOfMatches = controllerPane.matchesListComboBox();
        return new VBox(titleLabel, listOfMatches);
    }

    public static void main(String[] args) {
        launch(args); }
}
