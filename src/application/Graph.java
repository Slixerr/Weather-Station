package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Graph {
    final StringProperty nameProperty = new SimpleStringProperty();
    
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
    
    public void setName(String name) {
        nameProperty.setValue(name);
    }
    
}