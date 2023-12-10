package com.example.roulette.controller;

import com.example.roulette.model.Ball;
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
        drawImages();

        spinBTN.setOnAction((ActionEvent event) -> {
            RouletteSpin rouletteSpin = new RouletteSpin(canvas);
            rouletteSpin.start();
            try{
                rouletteSpin.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Ball ball = new Ball(canvas);
            ball.start();
            try{
                ball.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public void drawImages(){
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/roulette0.png").toExternalForm())), 0, 0, 280, 280);
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/board.png").toExternalForm())), 0, 360, 800, 300);
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/list.png").toExternalForm())), 0, 260, 800, 100);
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/chip10.png").toExternalForm())), 140, 668, 50, 50);
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/chip100.png").toExternalForm())), 260, 668, 50, 50);
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/chip1000.png").toExternalForm())), 380, 668, 50, 50);
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/chip10000.png").toExternalForm())), 500, 668, 50, 50);
    }






}