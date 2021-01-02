/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.Graph;
import application.Window;
import model.Model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.sf.marineapi.nmea.event.AbstractSentenceListener;
import net.sf.marineapi.nmea.io.SentenceReader;
import net.sf.marineapi.nmea.sentence.HDGSentence;
import net.sf.marineapi.nmea.sentence.MDASentence;
import net.sf.marineapi.nmea.sentence.MWVSentence;
import net.sf.marineapi.nmea.util.Position;

/**
 *
 * @author jsoler
 */
public class FXMLMainController implements Initializable {

    @FXML
    private Label twdLabel;
    @FXML
    private Label twsLabel;
    @FXML
    private Label presionLabel;
    @FXML
    private Label tempLabel;

    private Model model;
    @FXML
    private Label timeLabel;
    
    Graph graph = Graph.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = Model.getInstance();

        //==================================================================
        // anyadimos listener para que cuando cambie el valor en el modelo 
        //se actualice su valor en su correspondiente representacion grafica
        model.barometricPressureProperty().addListener((a, b, c) -> {
            String dat = String.valueOf(c) + " " + model.getBarometricUnit();
            Platform.runLater(() -> {
                presionLabel.setText(dat);
            });
        });
        model.TEMPProperty().addListener((a, b, c) -> {
            String dat = String.valueOf(c) + " ºcelsius";
            Platform.runLater(() -> {
                tempLabel.setText(dat);
            });
        });

        model.TWDProperty().addListener((a, b, c) -> {
            String dat = String.valueOf(c) + "º";
            Platform.runLater(() -> {
                twdLabel.setText(dat);
            });
        });
        model.TWSProperty().addListener((a, b, c) -> {
            String dat = String.valueOf(c) + "Kn";
            Platform.runLater(() -> {
                twsLabel.setText(dat);
            });
        });

    }
    
    @FXML
    private void openSettings(ActionEvent event) {
        FXMLStartController.show(Window.SETTINGS);
    }

    @FXML
    private void powerOff(ActionEvent event) {
        Model.getInstance().close();
        ((Stage) timeLabel.getScene().getWindow()).close();
    }

    @FXML
    private void openPresionGraph(ActionEvent event) {
        graph.setName("Presión");
        FXMLStartController.show(Window.GRAPH);
    }

    @FXML
    private void openTemperaturaGraph(ActionEvent event) {
        graph.setName("Temperatura");
        FXMLStartController.show(Window.GRAPH);
    }

    @FXML
    private void openTwdGraph(ActionEvent event) {
        graph.setName("TWD");
        graph.setData(model.getTWDSerie().getData());
        FXMLStartController.show(Window.GRAPH);
    }

    @FXML
    private void openTwsGraph(ActionEvent event) {
        graph.setName("TWS");
        System.out.println(model.getTWSSerie().getData().size());
        graph.setData(model.getTWSSerie().getData());
        FXMLStartController.show(Window.GRAPH);
    }
}

