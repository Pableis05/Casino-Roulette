package com.example.roulette.model;

import com.example.roulette.ui.AppRun;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.Random;

public class Ball extends Thread{

    private int ballNumber;
    private Canvas canvas;
    private Random random;

    public Ball(Canvas canvas) {
        Random random = new Random();
        this.canvas = canvas;
        ballNumber = random.nextInt(39);
    }

    @Override
    public void run() {
        for (int i = 0; i < 39; i++) {
            System.out.println(i);
            drawSpin(i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/list.png").toExternalForm())), 0, 260, 800, 100);
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/ball.png").toExternalForm())), 4 + 21 * ballNumber, 320, 20, 20);
    }

    public void drawSpin(int i){
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/ball.png").toExternalForm())), 4 + 21 * i, 320, 20, 20);
    }

}
