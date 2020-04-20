package edu.bsu.cs222.view;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class YearListView extends VBox {
    private final ComboBox<String> yearListBox = new ComboBox<>();

    public YearListView(){
        VBox yearListBox = createYearListBox();
        getChildren().add(yearListBox);
    }

    private VBox createYearListBox() {
        yearListBox.setPromptText("Select a year");
        yearListBox.setItems(FXCollections.observableArrayList(
                "2019", "2017","2016","2015")
        );
        return new VBox(yearListBox);
    }
}
