package controller;

import application.Graph;
import application.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
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
        graphLineChart.setCreateSymbols(false);
        graphName.textProperty().bind(graph.getNameProperty());
        graphLineChart.getData().add(new Series(graph.getData()));
    }

    @FXML
    private void backToMain(ActionEvent event) {
        FXMLStartController.show(Window.MAIN);
    }
}
