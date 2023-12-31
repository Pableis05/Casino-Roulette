package com.example.roulette.ui;

import com.example.roulette.controller.AudioManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AppRun extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppRun.class.getResource("gameplay.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),801, 730);
        stage.setTitle("Roulette Pableis");
        stage.getIcons().add(new Image(AppRun.class.getResource("sprites/icon.png").toExternalForm()));
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}