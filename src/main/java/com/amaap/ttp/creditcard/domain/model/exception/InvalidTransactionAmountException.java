package com.amaap.ttp.creditcard.domain.model.exception;

public class InvalidTransactionAmountException extends InvalidTransactionException{
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}
