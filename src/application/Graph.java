package application;

import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import model.Model;

public class Graph {
    final StringProperty nameProperty = new SimpleStringProperty();
    final BooleanProperty dataSwitchProperty = new SimpleBooleanProperty();
    Series series = new Series();
    
    private static final Graph graph = new Graph();
    
    private Graph() {}
    
    public static final Graph getInstance() {
        return graph;
    }

    public String getName() {
        return nameProperty.getValue();
    }
    
    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public Series getSeries() {
        return series;
    }
    
    public void setName(String name) {
        nameProperty.setValue(name);
    }
    
    public void setSeries(Series series) {
        this.series = series;
        dataSwitchProperty.setValue(!dataSwitchProperty.getValue());
    }
    
    public BooleanProperty seriesSwitchedProperty() {
        return dataSwitchProperty;
    }
}