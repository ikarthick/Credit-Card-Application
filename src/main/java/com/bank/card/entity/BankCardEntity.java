package com.bank.card.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "BANK_CARD")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "limit_amount")
    private Double limitAmount;
    @Column(name = "issuance_fee")
    private Double issuanceFee;
    @Column(name = "reward_points_per_dolor")
    private Double rewardPointsPerDolor;
    @OneToOne(mappedBy = "bankCard")
    private CardTransactionSummaryEntity cardTransactionSummaryEntity;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

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

    public CardTransactionSummaryEntity getCardTransactionSummaryEntity() {
        return cardTransactionSummaryEntity;
    }

    public void setCardTransactionSummaryEntity(CardTransactionSummaryEntity cardTransactionSummaryEntity) {
        this.cardTransactionSummaryEntity = cardTransactionSummaryEntity;
    }
}
