package com.example.roulette.controller;

import com.example.roulette.model.RouletteSpin;
import com.example.roulette.ui.AppRun;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RouletteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    @FXML
    private Button spinBTN;

    private GraphicsContext gc;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        RouletteSpin rouletteSpin = new RouletteSpin(canvas);

        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/roulette0.png").toExternalForm())), 0, 0, 200, 200);
        spinBTN.setOnAction((ActionEvent event) -> {
            rouletteSpin.start();
        });

    }







}