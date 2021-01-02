package application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public enum Window {
    MAIN ("/view/FXMLDocument.fxml"),
    SETTINGS ("/view/FXMLSettings.fxml");

    final String rootPath;
    
    Window(String rootPath) {
        this.rootPath = rootPath;
    }
    
    static int size() {
        return values().length;
    }
    
    public Parent load() {
        try {
            return new FXMLLoader(getClass().getResource(this.rootPath)).load();
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<Window> listValues() {
        return Arrays.asList(values());
    }
    
    public int position() {
        Window[] values = values();
        for (int i = 0; i < values.length; i++) {
            if (this.equals(values[i])) return i;
        }
        return -1;
    }
}
