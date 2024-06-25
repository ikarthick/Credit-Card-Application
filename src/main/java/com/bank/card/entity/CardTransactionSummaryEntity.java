package com.bank.card.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CARD_TRANSACTION_SUMMARY")
public class CardTransactionSummaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardTransactionSummaryId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "cardId")
    private BankCardEntity bankCard;
    @Column(name = "amount_balance")
    private Double amountBalance;
    @Column(name = "reward_points")
    private Double rewardPoints;

    public Integer getCardTransactionSummaryId() {
        return cardTransactionSummaryId;
    }

    public void setCardTransactionSummaryId(Integer cardTransactionSummaryId) {
        this.cardTransactionSummaryId = cardTransactionSummaryId;
    }

    public BankCardEntity getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCardEntity bankCard) {
        this.bankCard = bankCard;
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

    public CardTransactionSummaryEntity(Integer cardTransactionSummaryId, BankCardEntity bankCard, Double amountBalance, Double rewardPoints) {
        this.cardTransactionSummaryId = cardTransactionSummaryId;
        this.bankCard = bankCard;
        this.amountBalance = amountBalance;
        this.rewardPoints = rewardPoints;
    }

    public CardTransactionSummaryEntity() {
    }
}
