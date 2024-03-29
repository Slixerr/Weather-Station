/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Model;

/**
 *
 * @author jsoler
 */
public class EstacionMetereologica extends Application {
    
    public static Scene mainScene;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLStart.fxml"));
        
        Scene scene = new Scene(root);
        
        setScene(scene);
        
        EstacionMetereologica.mainScene.getStylesheets().add("/view/main-view.css");
  
        stage.setScene(scene);
        stage.setHeight(480);
        stage.setWidth(800);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setOnCloseRequest(e->{
            System.exit(0);
        });

    }
    
    static void setScene(Scene scene) {
        mainScene = scene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model.getInstance().sizeDataWindChartProperty().setValue(300);
        launch(args);
    }
    
}
