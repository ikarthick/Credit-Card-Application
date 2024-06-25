package com.bank.card.dto;

public class CardTransactionSummaryDto {
    private String cardName;
    private Double amountBalance;
    private Double rewardPoints;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Double getAmountBalance() {
        return amountBalance;
    }

    public void setAmountBalance(Double amountBalance) {
        this.amountBalance = amountBalance;
    }

    public Double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
