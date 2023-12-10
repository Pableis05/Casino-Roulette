package com.example.roulette.model;

import com.example.roulette.model.enums.BetCoinType;
import com.example.roulette.model.enums.BetCoinPosition;

public class BetCoin {

    private BetCoinType betCoinType;
    private BetCoinPosition betCoinPosition;

    private int multiplier;

    public BetCoin(BetCoinType betCoinType, BetCoinPosition betCoinPosition) {
        this.betCoinType = betCoinType;
        this.betCoinPosition = betCoinPosition;
    }

    public BetCoinType getBetCoinType() {
        return betCoinType;
    }

    public void setBetCoinType(BetCoinType betCoinType) {
        this.betCoinType = betCoinType;
    }

    public BetCoinPosition getBetCoinPosition() {
        return betCoinPosition;
    }

    public void setBetCoinPosition(BetCoinPosition betCoinPosition) {
        this.betCoinPosition = betCoinPosition;
    }


}
