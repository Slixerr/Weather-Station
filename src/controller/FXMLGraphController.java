package controller;

import application.Graph;
import application.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import model.Model;


public class FXMLGraphController implements Initializable {
    
    Graph graph = Graph.getInstance();
    
    @FXML
    private Label graphName;
    @FXML
    private LineChart<String, Number> graphLineChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        graphName.textProperty().bind(graph.getNameProperty());
        graph.seriesSwitchedProperty().addListener(c -> {
            graphLineChart.getData().clear();
            graphLineChart.getData().add(graph.getSeries());
        });
    }

    @FXML
    private void backToMain(ActionEvent event) {
        FXMLStartController.show(Window.MAIN);
    }
}
