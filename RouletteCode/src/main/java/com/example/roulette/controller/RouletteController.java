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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    private int betMoney, lastBetMoney, lastCurrentMoney;

    @FXML
    private RadioButton tenChip, hundredChip, thousandChip, tenThousandChip;

    @FXML
    private CheckBox cb0,cb00, cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10,
            cb11, cb12, cb13, cb14, cb15, cb16, cb17, cb18, cb19, cb20,
            cb21, cb22, cb23, cb24, cb25, cb26, cb27, cb28, cb29, cb30,
            cb31, cb32, cb33, cb34, cb35, cb36, row1, row2, row3, dozen1, dozen2, dozen3, eighteen, even, red, black, odd, nineteen;

    private ArrayList<CheckBox> checkBoxes;

    public RouletteController() {
        betCoins = new ArrayList<>();
        currentMoney = 10000;
        betMoney = 0;
        lastBetMoney = 0;
        tenChip = new RadioButton();
        hundredChip = new RadioButton();
        thousandChip = new RadioButton();
        tenThousandChip = new RadioButton();
        checkBoxes = new ArrayList<>();
        lastCurrentMoney = 0;
    }

    public void addCheckBoxes(){
        checkBoxes.add(cb0);
        checkBoxes.add(cb00);
        checkBoxes.add(cb1);
        checkBoxes.add(cb2);
        checkBoxes.add(cb3);
        checkBoxes.add(cb4);
        checkBoxes.add(cb5);
        checkBoxes.add(cb6);
        checkBoxes.add(cb7);
        checkBoxes.add(cb8);
        checkBoxes.add(cb9);
        checkBoxes.add(cb10);
        checkBoxes.add(cb11);
        checkBoxes.add(cb12);
        checkBoxes.add(cb13);
        checkBoxes.add(cb14);
        checkBoxes.add(cb15);
        checkBoxes.add(cb16);
        checkBoxes.add(cb17);
        checkBoxes.add(cb18);
        checkBoxes.add(cb19);
        checkBoxes.add(cb20);
        checkBoxes.add(cb21);
        checkBoxes.add(cb22);
        checkBoxes.add(cb23);
        checkBoxes.add(cb24);
        checkBoxes.add(cb25);
        checkBoxes.add(cb26);
        checkBoxes.add(cb27);
        checkBoxes.add(cb28);
        checkBoxes.add(cb29);
        checkBoxes.add(cb30);
        checkBoxes.add(cb31);
        checkBoxes.add(cb32);
        checkBoxes.add(cb33);
        checkBoxes.add(cb34);
        checkBoxes.add(cb35);
        checkBoxes.add(cb36);
        checkBoxes.add(row1);
        checkBoxes.add(row2);
        checkBoxes.add(row3);
        checkBoxes.add(dozen1);
        checkBoxes.add(dozen2);
        checkBoxes.add(dozen3);
        checkBoxes.add(eighteen);
        checkBoxes.add(even);
        checkBoxes.add(red);
        checkBoxes.add(black);
        checkBoxes.add(odd);
        checkBoxes.add(nineteen);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawImages();
        tenChip.setSelected(true);

        currentAmount.setText("Current Amount: $" + currentMoney);
        currentBet.setText("Current Bet: $" + 0);
        lastBet.setText("Last Bet: $" + lastBetMoney);
        profit.setText("Profit bet: $" + 0);
        balance.setText("Total balance: $" + (currentMoney - 10000));

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    Platform.runLater(() -> {
                        tenChip.setOnAction((ActionEvent event) -> {
                            tenChip.setSelected(true);
                            hundredChip.setSelected(false);
                            thousandChip.setSelected(false);
                            tenThousandChip.setSelected(false);
                        });
                        hundredChip.setOnAction((ActionEvent event) -> {
                            tenChip.setSelected(false);
                            hundredChip.setSelected(true);
                            thousandChip.setSelected(false);
                            tenThousandChip.setSelected(false);
                        });
                        thousandChip.setOnAction((ActionEvent event) -> {
                            tenChip.setSelected(false);
                            hundredChip.setSelected(false);
                            thousandChip.setSelected(true);
                            tenThousandChip.setSelected(false);
                        });
                        tenThousandChip.setOnAction((ActionEvent event) -> {
                            tenChip.setSelected(false);
                            hundredChip.setSelected(false);
                            thousandChip.setSelected(false);
                            tenThousandChip.setSelected(true);
                        });

                        profit.setText("Profit bet: $" + 0);
                        addCheckBoxes();

                        for (CheckBox checkBox : checkBoxes) {
                            checkBoxAction(checkBox, getCurrentBetCoinType(), getBetCoinPositionCheckBox(checkBox));
                        }

                        currentAmount.setText("Current Amount: $" + currentMoney);
                        currentBet.setText("Current Bet: $" + betMoney);

                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        spinBTN.setOnAction((ActionEvent event) -> {
            spinBTN.setDisable(true);
            canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/list.png").toExternalForm())), 0, 260, 800, 100);
            tenChip.setDisable(true);
            hundredChip.setDisable(true);
            thousandChip.setDisable(true);
            tenThousandChip.setDisable(true);
            for (CheckBox checkBox : checkBoxes) {
                checkBox.setDisable(true);
            }

            RouletteSpin rouletteSpin = new RouletteSpin(canvas);
            rouletteSpin.start();
            try{
                rouletteSpin.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Ball ball = new Ball(canvas);
            ballNumber = ball.getBallNumber();
            System.out.println(ballNumber + " ballNumber");
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
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/board.png").toExternalForm())), 0, 360, 800, 300);
                    Platform.runLater(() -> {
                        spinBTN.setDisable(false);
                        tenChip.setDisable(false);
                        hundredChip.setDisable(false);
                        thousandChip.setDisable(false);
                        tenThousandChip.setDisable(false);
                        for (CheckBox checkBox : checkBoxes) {
                            checkBox.setDisable(false);
                        }

                        lastCurrentMoney = currentMoney + betMoney;

                        checkWinners();
                        betCoins.clear();
                        for (CheckBox checkBox : checkBoxes) {
                            if(checkBox.isSelected())
                                checkBox.setSelected(false);
                        }

                        profit.setText("Profit bet: $" + (currentMoney - lastCurrentMoney));
                        currentAmount.setText("Current Amount: $" + currentMoney);
                        currentBet.setText("Current Bet: $" + 0);
                        lastBet.setText("Last Bet: $" + lastBetMoney);
                        balance.setText("Total balance: $" + (currentMoney - 10000));
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    public BetCoinPosition getBetCoinPositionCheckBox(CheckBox checkBox){
        if(checkBox == cb0){
            return BetCoinPosition.ZERO;
        } else if(checkBox == cb00){
            return BetCoinPosition.DOUBLE_ZERO;
        } else if(checkBox == cb1){
            return BetCoinPosition.ONE;
        } else if(checkBox == cb2){
            return BetCoinPosition.TWO;
        } else if(checkBox == cb3){
            return BetCoinPosition.THREE;
        } else if(checkBox == cb4){
            return BetCoinPosition.FOUR;
        } else if(checkBox == cb5){
            return BetCoinPosition.FIVE;
        } else if(checkBox == cb6){
            return BetCoinPosition.SIX;
        } else if(checkBox == cb7){
            return BetCoinPosition.SEVEN;
        } else if(checkBox == cb8){
            return BetCoinPosition.EIGHT;
        } else if(checkBox == cb9){
            return BetCoinPosition.NINE;
        } else if(checkBox == cb10){
            return BetCoinPosition.TEN;
        } else if(checkBox == cb11){
            return BetCoinPosition.ELEVEN;
        } else if(checkBox == cb12){
            return BetCoinPosition.TWELVE;
        } else if(checkBox == cb13){
            return BetCoinPosition.THIRTEEN;
        } else if(checkBox == cb14){
            return BetCoinPosition.FOURTEEN;
        } else if(checkBox == cb15){
            return BetCoinPosition.FIFTEEN;
        } else if(checkBox == cb16){
            return BetCoinPosition.SIXTEEN;
        } else if(checkBox == cb17){
            return BetCoinPosition.SEVENTEEN;
        } else if(checkBox == cb18){
            return BetCoinPosition.EIGHTEEN;
        } else if(checkBox == cb19){
            return BetCoinPosition.NINETEEN;
        } else if(checkBox == cb20){
            return BetCoinPosition.TWENTY;
        } else if(checkBox == cb21){
            return BetCoinPosition.TWENTY_ONE;
        } else if(checkBox == cb22){
            return BetCoinPosition.TWENTY_TWO;
        } else if(checkBox == cb23){
            return BetCoinPosition.TWENTY_THREE;
        } else if(checkBox == cb24) {
            return BetCoinPosition.TWENTY_FOUR;
        } else if(checkBox == cb25){
            return BetCoinPosition.TWENTY_FIVE;
        } else if(checkBox == cb26){
            return BetCoinPosition.TWENTY_SIX;
        } else if(checkBox == cb27){
            return BetCoinPosition.TWENTY_SEVEN;
        } else if(checkBox == cb28){
            return BetCoinPosition.TWENTY_EIGHT;
        } else if(checkBox == cb29){
            return BetCoinPosition.TWENTY_NINE;
        } else if(checkBox == cb30){
            return BetCoinPosition.THIRTY;
        } else if(checkBox == cb31){
            return BetCoinPosition.THIRTY_ONE;
        } else if(checkBox == cb32){
            return BetCoinPosition.THIRTY_TWO;
        } else if(checkBox == cb33){
            return BetCoinPosition.THIRTY_THREE;
        } else if(checkBox == cb34){
            return BetCoinPosition.THIRTY_FOUR;
        } else if(checkBox == cb35){
            return BetCoinPosition.THIRTY_FIVE;
        } else if(checkBox == cb36){
            return BetCoinPosition.THIRTY_SIX;
        } else if(checkBox == row1){
            return BetCoinPosition.FIRST_COLUMN;
        } else if(checkBox == row2){
            return BetCoinPosition.SECOND_COLUMN;
        } else if(checkBox == row3){
            return BetCoinPosition.THIRD_COLUMN;
        } else if(checkBox == dozen1){
            return BetCoinPosition.FIRST_DOZEN;
        } else if(checkBox == dozen2){
            return BetCoinPosition.SECOND_DOZEN;
        } else if(checkBox == dozen3){
            return BetCoinPosition.THIRD_DOZEN;
        } else if(checkBox == eighteen){
            return BetCoinPosition.ONE_TO_EIGHTEEN;
        } else if(checkBox == even){
            return BetCoinPosition.EVEN;
        } else if(checkBox == red){
            return BetCoinPosition.RED;
        } else if(checkBox == black){
            return BetCoinPosition.BLACK;
        } else if(checkBox == odd){
            return BetCoinPosition.ODD;
        } else if(checkBox == nineteen){
            return BetCoinPosition.NINETEEN_TO_THIRTY_SIX;
        }
        return null;
    }

    public BetCoinType getCurrentBetCoinType(){
        if(tenChip.isSelected()){
            return BetCoinType.TEN;
        } else if(hundredChip.isSelected()){
            return BetCoinType.HUNDRED;
        } else if(thousandChip.isSelected()){
            return BetCoinType.THOUSAND;
        } else if(tenThousandChip.isSelected()){
            return BetCoinType.TEN_THOUSAND;
        }
        return null;
    }

    public void checkBoxAction(CheckBox checkBox, BetCoinType betCoinType, BetCoinPosition betCoinPosition){
        checkBox.setOnAction((ActionEvent event) -> {
            if(checkBox.isSelected()){
                addBet(betCoinType, betCoinPosition);
                printBetCoins(betCoinType, betCoinPosition);
            }else {
                for (BetCoin betCoin : betCoins) {
                    if(betCoin.getBetCoinPosition() == betCoinPosition){
                        betMoneyDeleteUpdate(betCoin);
                    }
                }
                betCoins.removeIf(betCoin -> betCoin.getBetCoinPosition() == betCoinPosition);
                removePaintBetCoin();
            }
        });
    }

    public void removePaintBetCoin(){
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/board.png").toExternalForm())), 0, 360, 800, 300);
        for (BetCoin betCoin : betCoins) {
            printBetCoins(betCoin.getBetCoinType(), betCoin.getBetCoinPosition());
        }

    }

    public void printBetCoins(BetCoinType betCoinType, BetCoinPosition betCoinPosition){
        String betCoinTypeString = "";
        int[] position = obtainPositionBet(betCoinPosition);
        switch (betCoinType){
            case TEN:
                betCoinTypeString = "10";
                break;
            case HUNDRED:
                betCoinTypeString = "100";
                break;
            case THOUSAND:
                betCoinTypeString = "1000";
                break;
            case TEN_THOUSAND:
                betCoinTypeString = "10000";
                break;
        }
        canvas.getGraphicsContext2D().drawImage(new Image((AppRun.class.getResource("sprites/chip" + betCoinTypeString +".png").toExternalForm())), position[0], position[1] , 35, 35);
    }

    public int[] obtainPositionBet(BetCoinPosition betCoinPosition){
        switch (betCoinPosition){
            case ONE -> {return new int[]{80, 485}; }
            case FOUR -> {return new int[]{136, 485}; }
            case SEVEN -> {return new int[]{191, 485}; }
            case TEN -> {return new int[]{246, 485}; }
            case THIRTEEN -> {return new int[]{302, 485}; }
            case SIXTEEN -> {return new int[]{357, 485}; }
            case NINETEEN -> {return new int[]{412, 485}; }
            case TWENTY_TWO -> {return new int[]{468, 485}; }
            case TWENTY_FIVE -> {return new int[]{523, 485}; }
            case TWENTY_EIGHT -> {return new int[]{580, 485}; }
            case THIRTY_ONE -> {return new int[]{635, 485}; }
            case THIRTY_FOUR -> {return new int[]{690, 485}; }
            case FIRST_COLUMN -> {return new int[]{750, 485}; }

            case TWO -> {return new int[]{80, 432}; }
            case FIVE -> {return new int[]{136, 432}; }
            case EIGHT -> {return new int[]{191, 432}; }
            case ELEVEN -> {return new int[]{246, 432}; }
            case FOURTEEN -> {return new int[]{302, 432}; }
            case SEVENTEEN -> {return new int[]{357, 432}; }
            case TWENTY -> {return new int[]{412, 432}; }
            case TWENTY_THREE -> {return new int[]{468, 432}; }
            case TWENTY_SIX -> {return new int[]{523, 432}; }
            case TWENTY_NINE -> {return new int[]{580, 432}; }
            case THIRTY_TWO -> {return new int[]{635, 432}; }
            case THIRTY_FIVE -> {return new int[]{690, 432}; }
            case SECOND_COLUMN -> {return new int[]{750, 432}; }

            case THREE -> {return new int[]{80, 379}; }
            case SIX -> {return new int[]{136, 379}; }
            case NINE -> {return new int[]{191, 379}; }
            case TWELVE -> {return new int[]{246, 379}; }
            case FIFTEEN -> {return new int[]{302, 379}; }
            case EIGHTEEN -> {return new int[]{357, 379}; }
            case TWENTY_ONE -> {return new int[]{412, 379}; }
            case TWENTY_FOUR -> {return new int[]{468, 379}; }
            case TWENTY_SEVEN -> {return new int[]{523, 379}; }
            case THIRTY -> {return new int[]{580, 379}; }
            case THIRTY_THREE -> {return new int[]{635, 379}; }
            case THIRTY_SIX -> {return new int[]{690, 379}; }
            case THIRD_COLUMN -> {return new int[]{750, 379}; }

            case ZERO -> {return new int[]{27, 475}; }
            case DOUBLE_ZERO -> {return new int[]{27, 394}; }
            case ONE_TO_EIGHTEEN -> {return new int[]{108, 595}; }
            case NINETEEN_TO_THIRTY_SIX -> {return new int[]{670, 595}; }
            case EVEN -> {return new int[]{220, 595}; }
            case ODD -> {return new int[]{560, 595}; }
            case RED -> {return new int[]{340, 595}; }
            case BLACK -> {return new int[]{449, 595}; }
            case FIRST_DOZEN -> {return new int[]{163, 542}; }
            case SECOND_DOZEN -> {return new int[]{390, 542}; }
            case THIRD_DOZEN -> {return new int[]{609, 542}; }

        }
        return null;
    }

    public void addBet(BetCoinType betCoinType, BetCoinPosition betCoinPosition){
        BetCoin betCoin = new BetCoin(betCoinType, betCoinPosition);
        betCoins.add(betCoin);
        betMoneyUpdate(betCoin);
    }

    public void betMoneyUpdate(BetCoin betCoin){
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

    public void betMoneyDeleteUpdate(BetCoin betCoin){
        if(betCoin.getBetCoinType() == BetCoinType.TEN){
            betMoney -= 10;
            currentMoney += 10;
        } else if(betCoin.getBetCoinType() == BetCoinType.HUNDRED){
            betMoney -= 100;
            currentMoney += 100;
        } else if(betCoin.getBetCoinType() == BetCoinType.THOUSAND){
            betMoney -= 1000;
            currentMoney += 1000;
        } else if(betCoin.getBetCoinType() == BetCoinType.TEN_THOUSAND){
            betMoney -= 10000;
            currentMoney += 10000;
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
        lastBetMoney = betMoney;
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