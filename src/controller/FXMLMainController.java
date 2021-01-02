package controller;

import application.Graph;
import application.Window;
import model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
        graph.setSeries(model.getBPSerie());
        FXMLStartController.show(Window.GRAPH);
    }

    @FXML
    private void openTemperaturaGraph(ActionEvent event) {
        graph.setName("Temperatura");
        graph.setSeries(model.getTEMPSerie());
        FXMLStartController.show(Window.GRAPH);
    }

    @FXML
    private void openTwdGraph(ActionEvent event) {
        graph.setName("TWD");
        graph.setSeries(model.getTWDSerie());
        FXMLStartController.show(Window.GRAPH);
    }

    @FXML
    private void openTwsGraph(ActionEvent event) {
        graph.setName("TWS");
        graph.setSeries(model.getTWSSerie());
        FXMLStartController.show(Window.GRAPH);
    }
}

