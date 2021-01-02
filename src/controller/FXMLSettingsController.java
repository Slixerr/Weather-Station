/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.stage.FileChooser;
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
    
    private String[] themeOptions = new String[]{"Blanco","Negro"};
    
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
        
        BooleanBinding connectionBinding = Bindings.notEqual(conectionChoiceBox.valueProperty(), Connection.FICHERO);
        fileLabel.disableProperty().bind(connectionBinding);
        ficheroLabel.disableProperty().bind(connectionBinding);
        addFileButton.disableProperty().bind(connectionBinding);
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

        // ficheroChooser.setSelectedExtensionFilter(new ExtensionFilter("ficheros NMEA", "*.NMEA"));
        ficheroChooser.setTitle("fichero datos NMEA");

        File ficheroNMEA = ficheroChooser.showOpenDialog(ficheroLabel.getScene().getWindow());
        if (ficheroNMEA != null) {
            // ========================================================
            // NO se comprueba que se trata de un fichero de datos NMEA
            // esto es una demos
            ficheroLabel.setText("fichero: " + ficheroNMEA.getName());
            // ========================================================
            // se pone en marcha el proceso para recibir tramas
            model.addSentenceReader(ficheroNMEA);
        }
    }
}
