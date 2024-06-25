package com.bank.card.common.exception;

public class BankBalanceInsufficientException extends RuntimeException {
    public BankBalanceInsufficientException(String message) {
        super(message);
    }

    public BankBalanceInsufficientException(String message, Throwable cause) {
        super(message, cause);
    }
}
