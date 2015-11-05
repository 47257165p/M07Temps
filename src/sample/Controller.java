package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class Controller {
    public ListView <String> lv1;
    private ObservableList<String> items =  FXCollections.observableArrayList();
    private File inFile = new File("/home/47257165p/IdeaProjects/M07 Desenvolupament d'interficies/Temps/src/sample/forecast.xml");

    public void initialize ()
    {

        lv1.setEditable(true);
        lv1.setPrefWidth(100);
        lv1.setPrefHeight(70);
        lv1.setItems(items);
        items.addAll(XMLDOM.lectorDOM(inFile));

        lv1.setItems(items);
    }
}
