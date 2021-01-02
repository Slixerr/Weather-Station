package controller;

import exceptions.MultipleStartContainersException;
import application.Window;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class FXMLStartController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    
    private Parent[] root;
    
    private static FXMLStartController instance = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root = Window.listValues().stream().map(Window::load).toArray(Parent[]::new);
        if (instance != null) throw new MultipleStartContainersException();
        instance = this;
        
        FXMLStartController.show(Window.MAIN);
    }
    
    public static void show(Window window) {
        instance.mainPane.getChildren().setAll(instance.root[window.position()]);
    }
}
