package com.amaap.ttp.creditcard.domain.model.exception;

public class InvalidTransactionIdException extends InvalidTransactionException{
    public InvalidTransactionIdException(String message) {
        super(message);
    }
}
