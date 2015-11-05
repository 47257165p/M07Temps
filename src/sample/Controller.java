package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class Controller {
    private ListView lv1;

    public Controller ()
    {
        lv1 = new ListView();
        ObservableList items =FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App","Single", "Double", "Suite", "Family App","Single", "Double", "Suite", "Family App");
        lv1.setPrefWidth(100);
        lv1.setPrefHeight(70);
        lv1.setItems(items);
    }
}
