package application;

import javafx.beans.InvalidationListener;
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
    final ObservableList<Data<String, Number>> data = FXCollections.observableArrayList();
    ObservableList<Data<String, Number>> pastData = FXCollections.observableArrayList();
    
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

    public ObservableList<Data<String, Number>> getData() {
        return data;
    }
    
    public void setName(String name) {
        nameProperty.setValue(name);
    }
    
    public void setData(ObservableList<Data<String, Number>> data) {
        ListChangeListener<? super Data<String, Number>> changeListener = (Change<? extends Data<String, Number>> c) -> {
            while (c.next()) {
                c.getRemoved().forEach(this.data::remove);
                c.getAddedSubList().forEach(this.data::add);
            }
        };
        pastData.removeListener(changeListener);
        pastData = data;
        this.data.clear();
        this.data.addAll(data);
        data.addListener(changeListener);
    }
}