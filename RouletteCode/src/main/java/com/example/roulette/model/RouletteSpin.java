package com.example.roulette.model;

import com.example.roulette.ui.AppRun;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.Random;

public class RouletteSpin extends Thread{

    private String[] scene;

    private Canvas canvas;

    private int spins;

    public RouletteSpin(Canvas canvas) {
        scene = new String[7];
        scene[0] = "sprites/roulette1.png";
        scene[1] = "sprites/roulette2.png";
        scene[2] = "sprites/roulette3.png";
        scene[3] = "sprites/roulette4.png";
        scene[4] = "sprites/roulette5.png";
        scene[5] = "sprites/roulette6.png";
        scene[6] = "sprites/roulette7.png";
        this.canvas= canvas;
        spins = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {

            canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource(scene[i]).toExternalForm())), 0, 0, 280, 260);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 1){
                spins++;
                if(spins == 6){
                    Random random = new Random();
                    i = random.nextInt(7);
                    canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource(scene[i]).toExternalForm())), 0, 0, 280, 260);
                    break;
                }
            }
            i = (i == 6) ? -1 : i;
        }

    }


}
