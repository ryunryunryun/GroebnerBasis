package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main classs
 */
public class Main extends Application {
    public static void main (String args[]) {
        launch(args);
    }

    @Override
    public void start (Stage stage) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("gui.fxml"));
            Scene scene = new Scene(root, 400, 600);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
