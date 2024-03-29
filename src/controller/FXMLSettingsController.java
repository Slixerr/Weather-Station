/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.EstacionMetereologica;
import application.IntegerSecondsConverter;
import application.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import model.Model;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class FXMLSettingsController implements Initializable {

    @FXML
    private Label fileLabel;
    @FXML
    private Label ficheroLabel;
    @FXML
    private Button addFileButton;
    @FXML
    private ChoiceBox<Theme> themeChoiceBox;
    @FXML
    private ChoiceBox<Connection> conectionChoiceBox;
    
    private Model model;
    
    @FXML
    private ChoiceBox<Integer> intervalChoiceBox;
    
    public static enum Theme {
        BLACK ("Negro"),
        WHITE ("Blanco");
        
        public final String name;
        
        Theme(String name) {
            this.name = name;
        }
        
        public static List<Theme> listValues() {
            return Arrays.asList(values());
        }

        @Override
        public String toString() {
            return name;
        }
    }
    
    public static enum Connection {
        PUERTO ("Puerto"),
        FICHERO ("Fichero");
        
        public final String name;
        
        Connection(String name) {
            this.name = name;
        }
        
        public static List<Connection> listValues() {
            return Arrays.asList(values());
        }
        
        @Override
        public String toString() {
            return name;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = Model.getInstance();
        
        themeChoiceBox.setItems(FXCollections.observableList(Theme.listValues()));
        themeChoiceBox.setValue(Theme.WHITE);
        
        conectionChoiceBox.setItems(FXCollections.observableList(Connection.listValues()));
        conectionChoiceBox.setValue(Connection.FICHERO);
        
        intervalChoiceBox.setConverter(new IntegerSecondsConverter());
        ObservableList<Integer> times = FXCollections.observableArrayList();
        for (int i = 300; i <= 600; i += 60) {
            times.add(i);
        }
        intervalChoiceBox.setItems(times);
        intervalChoiceBox.setValue(300);
        
        model.sizeDataWindChartProperty().bind(intervalChoiceBox.valueProperty());
        
        BooleanBinding connectionBinding = Bindings.notEqual(conectionChoiceBox.valueProperty(), Connection.FICHERO);
        fileLabel.disableProperty().bind(connectionBinding);
        ficheroLabel.disableProperty().bind(connectionBinding);
        addFileButton.disableProperty().bind(connectionBinding);
        
        themeChoiceBox.valueProperty().addListener((a,b,c) -> {
            if (c.equals(Theme.WHITE)) {
                EstacionMetereologica.mainScene.getStylesheets().add("/view/main-view.css");
                EstacionMetereologica.mainScene.getStylesheets().remove("/view/dark-view.css");
            } else {
                EstacionMetereologica.mainScene.getStylesheets().remove("/view/main-view.css");
                EstacionMetereologica.mainScene.getStylesheets().add("/view/dark-view.css");
            }
        });
    }    

    @FXML
    private void backToMain(ActionEvent event) {
        FXMLStartController.show(Window.MAIN);
    }

    @FXML
    private void cargarFichero(ActionEvent event) throws FileNotFoundException {
        FileChooser ficheroChooser = new FileChooser();
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        ficheroChooser.setInitialDirectory(new File(currentPath));
        ficheroChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ficheros NMEA", "*.NMEA"));

        ficheroChooser.setSelectedExtensionFilter(new ExtensionFilter("ficheros NMEA", "*.NMEA"));
        ficheroChooser.setTitle("fichero datos NMEA");

        File ficheroNMEA = ficheroChooser.showOpenDialog(ficheroLabel.getScene().getWindow());
        if (ficheroNMEA != null) {
            ficheroLabel.setText(ficheroNMEA.getName());
            
            model.addSentenceReader(ficheroNMEA);
        }
    }
}
