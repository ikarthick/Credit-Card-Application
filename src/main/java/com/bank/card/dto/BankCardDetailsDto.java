package com.bank.card.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public class BankCardDetailsDto {
    private String cardName;
    private Double limitAmount;
    private Double issuanceFee;
    private Double rewardPointsPerDolor;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Double getIssuanceFee() {
        return issuanceFee;
    }

    public void setIssuanceFee(Double issuanceFee) {
        this.issuanceFee = issuanceFee;
    }

    public Double getRewardPointsPerDolor() {
        return rewardPointsPerDolor;
    }

    public void setRewardPointsPerDolor(Double rewardPointsPerDolor) {
        this.rewardPointsPerDolor = rewardPointsPerDolor;
    }
}
