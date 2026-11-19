package com.mycompany.carrace;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Opens the race window and loads its FXML.
 *
 * @author Arin Kabir
 */
public class App extends Application {
    private CarRaceController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("CarRaceFXML.fxml"));
        stage.setScene(new Scene(loader.load()));
        controller = loader.getController();
        stage.setTitle("Car Race");
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        if (controller != null) {
            controller.stopAnimations();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
