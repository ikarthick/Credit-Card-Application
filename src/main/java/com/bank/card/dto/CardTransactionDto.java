package com.bank.card.dto;

public class CardTransactionDto {
    private String cardName;
    private Double amount;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Double getAmount() {
        return amount;
    }

    public CardTransactionDto(String cardName, Double amount) {
        this.cardName = cardName;
        this.amount = amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CardTransactionDto() {
    }
}
