package com.amaap.ttp.creditcard.domain.exception.transactionexception;

public class InvalidTransactionAmountException extends InvalidTransactionException{
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}
