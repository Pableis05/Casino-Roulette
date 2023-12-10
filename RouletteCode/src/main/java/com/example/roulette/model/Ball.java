package com.example.roulette.model;

import com.example.roulette.model.enums.BetCoinPosition;
import com.example.roulette.ui.AppRun;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.Random;

public class Ball extends Thread{

    private int ballNumber;
    private BetCoinPosition ballPosition;
    private BetCoinPosition even_odd;
    private BetCoinPosition dozen;
    private BetCoinPosition column;

    private BetCoinPosition half;

    private BetCoinPosition color;
    private Canvas canvas;

    public Ball(Canvas canvas) {
        Random random = new Random();
        this.canvas = canvas;
        ballNumber = random.nextInt(39);
    }

    @Override
    public void run() {
        for (int i = 0; i < 38; i++) {
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

    public BetCoinPosition getBallNumber() {

        switch (ballNumber){
            case 0:
                ballPosition = BetCoinPosition.DOUBLE_ZERO;
                break;
            case 1:
                ballPosition = BetCoinPosition.ONE;
                break;
            case 2:
                ballPosition = BetCoinPosition.THIRTEEN;
                break;
            case 3:
                ballPosition = BetCoinPosition.THIRTY_SIX;
                break;
            case 4:
                ballPosition = BetCoinPosition.TWENTY_FOUR;
                break;
            case 5:
                ballPosition = BetCoinPosition.THREE;
                break;
            case 6:
                ballPosition = BetCoinPosition.FIFTEEN;
                break;
            case 7:
                ballPosition = BetCoinPosition.THIRTY_FOUR;
                break;
            case 8:
                ballPosition = BetCoinPosition.TWENTY_TWO;
                break;
            case 9:
                ballPosition = BetCoinPosition.FIVE;
                break;
            case 10:
                ballPosition = BetCoinPosition.SEVENTEEN;
                break;
            case 11:
                ballPosition = BetCoinPosition.THIRTY_TWO;
                break;
            case 12:
                ballPosition = BetCoinPosition.TWENTY;
                break;
            case 13:
                ballPosition = BetCoinPosition.SEVEN;
                break;
            case 14:
                ballPosition = BetCoinPosition.ELEVEN;
                break;
            case 15:
                ballPosition = BetCoinPosition.THIRTY;
                break;
            case 16:
                ballPosition = BetCoinPosition.TWENTY_SIX;
                break;
            case 17:
                ballPosition = BetCoinPosition.NINE;
                break;
            case 18:
                ballPosition = BetCoinPosition.TWENTY_EIGHT;
                break;
            case 19:
                ballPosition = BetCoinPosition.ZERO;
                break;
            case 20:
                ballPosition = BetCoinPosition.TWO;
                break;
            case 21:
                ballPosition = BetCoinPosition.FOURTEEN;
                break;
            case 22:
                ballPosition = BetCoinPosition.THIRTY_FIVE;
                break;
            case 23:
                ballPosition = BetCoinPosition.TWENTY_THREE;
                break;
            case 24:
                ballPosition = BetCoinPosition.FOUR;
                break;
            case 25:
                ballPosition = BetCoinPosition.SIXTEEN;
                break;
            case 26:
                ballPosition = BetCoinPosition.THIRTY_THREE;
                break;
            case 27:
                ballPosition = BetCoinPosition.TWENTY_ONE;
                break;
            case 28:
                ballPosition = BetCoinPosition.SIX;
                break;
            case 29:
                ballPosition = BetCoinPosition.EIGHTEEN;
                break;
            case 30:
                ballPosition = BetCoinPosition.THIRTY_ONE;
                break;
            case 31:
                ballPosition = BetCoinPosition.NINETEEN;
                break;
            case 32:
                ballPosition = BetCoinPosition.EIGHT;
                break;
            case 33:
                ballPosition = BetCoinPosition.TWELVE;
                break;
            case 34:
                ballPosition = BetCoinPosition.TWENTY_NINE;
                break;
            case 35:
                ballPosition = BetCoinPosition.TWENTY_FIVE;
                break;
            case 36:
                ballPosition = BetCoinPosition.TEN;
                break;
            case 37:
                ballPosition = BetCoinPosition.TWENTY_SEVEN;
                break;
        }

        return ballPosition;
    }

    public BetCoinPosition getEven_odd() {
        if(ballNumber % 2 == 0){
            even_odd = BetCoinPosition.EVEN;
        } else {
            even_odd = BetCoinPosition.ODD;
        }
        return even_odd;
    }

    public BetCoinPosition getColor() {
        if(ballPosition == BetCoinPosition.ONE || ballPosition == BetCoinPosition.THREE || ballPosition == BetCoinPosition.FIVE || ballPosition == BetCoinPosition.SEVEN || ballPosition == BetCoinPosition.NINE || ballPosition == BetCoinPosition.TWELVE || ballPosition == BetCoinPosition.FOURTEEN || ballPosition == BetCoinPosition.SIXTEEN || ballPosition == BetCoinPosition.EIGHTEEN || ballPosition == BetCoinPosition.NINETEEN || ballPosition == BetCoinPosition.TWENTY_ONE || ballPosition == BetCoinPosition.TWENTY_THREE || ballPosition == BetCoinPosition.TWENTY_FIVE || ballPosition == BetCoinPosition.TWENTY_SEVEN || ballPosition == BetCoinPosition.THIRTY || ballPosition == BetCoinPosition.THIRTY_TWO || ballPosition == BetCoinPosition.THIRTY_FOUR || ballPosition == BetCoinPosition.THIRTY_SIX){
            color = BetCoinPosition.RED;
        } else {
            color = BetCoinPosition.BLACK;
        }
        return color;
    }

    public BetCoinPosition getDozen() {
        if(ballNumber >= 1 && ballNumber <= 12){
            dozen = BetCoinPosition.FIRST_DOZEN;
        } else if(ballNumber >= 13 && ballNumber <= 24){
            dozen = BetCoinPosition.SECOND_DOZEN;
        } else if(ballNumber >= 25 && ballNumber <= 36){
            dozen = BetCoinPosition.THIRD_DOZEN;
        }
        return dozen;
    }

    public BetCoinPosition getColumn() {
        if(ballNumber % 3 == 0){
            column = BetCoinPosition.FIRST_COLUMN;
        } else if(ballNumber % 3 == 1){
            column = BetCoinPosition.SECOND_COLUMN;
        } else if(ballNumber % 3 == 2){
            column = BetCoinPosition.THIRD_COLUMN;
        }
        return column;
    }

    public BetCoinPosition getHalf() {
        if(ballNumber >= 1 && ballNumber <= 18){
            half = BetCoinPosition.ONE_TO_EIGHTEEN;
        } else if(ballNumber >= 19 && ballNumber <= 36){
            half = BetCoinPosition.NINETEEN_TO_THIRTY_SIX;
        }
        return half;
    }



}
