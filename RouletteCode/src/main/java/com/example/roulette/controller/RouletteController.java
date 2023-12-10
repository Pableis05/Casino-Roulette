package com.example.roulette.controller;

import com.example.roulette.model.Ball;
import com.example.roulette.model.BetCoin;
import com.example.roulette.model.RouletteSpin;
import com.example.roulette.model.enums.BetCoinPosition;
import com.example.roulette.model.enums.BetCoinType;
import com.example.roulette.ui.AppRun;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RouletteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    @FXML
    private Button spinBTN;

    @FXML
    private Label currentAmount;

    @FXML
    private Label currentBet;

    @FXML
    private Label lastBet;

    @FXML
    private Label profit;

    @FXML
    private Label balance;

    private GraphicsContext gc;

    private BetCoinPosition ballNumber;
    private BetCoinPosition even_odd;
    private BetCoinPosition ballColor;
    private BetCoinPosition dozen;
    private BetCoinPosition column;
    private BetCoinPosition half;

    private ArrayList<BetCoin> betCoins;

    private int currentMoney;

    private int betMoney;


    public RouletteController() {
        betCoins = new ArrayList<>();
        currentMoney = 10000;
        betMoney = 0;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawImages();
        spinBTN.setOnAction((ActionEvent event) -> {
            canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/list.png").toExternalForm())), 0, 260, 800, 100);
            RouletteSpin rouletteSpin = new RouletteSpin(canvas);
            rouletteSpin.start();
            try{
                rouletteSpin.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Ball ball = new Ball(canvas);
            ballNumber = ball.getBallNumber();
            even_odd = ball.getEven_odd();
            ballColor = ball.getColor();
            dozen = ball.getDozen();
            column = ball.getColumn();
            half = ball.getHalf();
            ball.start();
            try{
                ball.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(() -> {
                try {
                    rouletteSpin.join();
                    ball.join();

                    Platform.runLater(() -> {

                        addBets();
                        betMoneyUpdate();
                        checkWinners();
                        betCoins.clear();
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    public void addBets(){
        betCoins.add(new BetCoin(BetCoinType.TEN, BetCoinPosition.ZERO));
        betCoins.add(new BetCoin(BetCoinType.TEN, BetCoinPosition.RED));
        betCoins.add(new BetCoin(BetCoinType.TEN, BetCoinPosition.EVEN));
        betCoins.add(new BetCoin(BetCoinType.TEN, BetCoinPosition.ONE_TO_EIGHTEEN));
        betCoins.add(new BetCoin(BetCoinType.TEN, BetCoinPosition.FIRST_COLUMN));
        betCoins.add(new BetCoin(BetCoinType.TEN, BetCoinPosition.FIRST_DOZEN));
    }

    public void betMoneyUpdate() {
        for (BetCoin betCoin : betCoins) {
            if(betCoin.getBetCoinType() == BetCoinType.TEN){
                betMoney += 10;
                currentMoney -= 10;
            } else if(betCoin.getBetCoinType() == BetCoinType.HUNDRED){
                betMoney += 100;
                currentMoney -= 100;
            } else if(betCoin.getBetCoinType() == BetCoinType.THOUSAND){
                betMoney += 1000;
                currentMoney -= 1000;
            } else if(betCoin.getBetCoinType() == BetCoinType.TEN_THOUSAND){
                betMoney += 10000;
                currentMoney -= 10000;
            }
        }
    }

    public void checkWinners(){
        for (BetCoin betCoin : betCoins) {
            int individualBetChip = checkAmountMoneyOfBetCoin(betCoin);
            if (betCoin.getBetCoinPosition() == ballNumber) {
                currentMoney += individualBetChip * 36;
            } else if (!(ballNumber == BetCoinPosition.DOUBLE_ZERO || ballNumber == BetCoinPosition.ZERO)) {
                if ((betCoin.getBetCoinPosition() == BetCoinPosition.RED && ballColor == BetCoinPosition.RED) || (betCoin.getBetCoinPosition() == BetCoinPosition.BLACK && ballColor == BetCoinPosition.BLACK)) {
                    currentMoney += individualBetChip * 2;
                } else if ((betCoin.getBetCoinPosition() == BetCoinPosition.EVEN && even_odd == BetCoinPosition.EVEN) || (betCoin.getBetCoinPosition() == BetCoinPosition.ODD && even_odd == BetCoinPosition.ODD)) {
                    currentMoney += individualBetChip * 2;
                } else if ((betCoin.getBetCoinPosition() == BetCoinPosition.ONE_TO_EIGHTEEN && half == BetCoinPosition.ONE_TO_EIGHTEEN) || (betCoin.getBetCoinPosition() == BetCoinPosition.NINETEEN_TO_THIRTY_SIX && half == BetCoinPosition.NINETEEN_TO_THIRTY_SIX)) {
                    currentMoney += individualBetChip  * 2;
                } else if ((betCoin.getBetCoinPosition() == BetCoinPosition.FIRST_COLUMN && column == BetCoinPosition.FIRST_COLUMN) || (betCoin.getBetCoinPosition() == BetCoinPosition.SECOND_COLUMN && column == BetCoinPosition.SECOND_COLUMN) || (betCoin.getBetCoinPosition() == BetCoinPosition.THIRD_COLUMN && column == BetCoinPosition.THIRD_COLUMN)) {
                    currentMoney += individualBetChip * 3;
                } else if ((betCoin.getBetCoinPosition() == BetCoinPosition.FIRST_DOZEN && dozen == BetCoinPosition.FIRST_DOZEN) || (betCoin.getBetCoinPosition() == BetCoinPosition.SECOND_DOZEN && dozen == BetCoinPosition.SECOND_DOZEN) || (betCoin.getBetCoinPosition() == BetCoinPosition.THIRD_DOZEN && dozen == BetCoinPosition.THIRD_DOZEN)) {
                    currentMoney += individualBetChip * 3;
                }
            }
        }
        betMoney = 0;
    }

    public int checkAmountMoneyOfBetCoin(BetCoin betCoin){
        if(betCoin.getBetCoinType() == BetCoinType.TEN){
            return 10;
        } else if(betCoin.getBetCoinType() == BetCoinType.HUNDRED){
            return 100;
        } else if(betCoin.getBetCoinType() == BetCoinType.THOUSAND){
            return 1000;
        } else if(betCoin.getBetCoinType() == BetCoinType.TEN_THOUSAND){
            return 10000;
        }
        return 0;
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