package com.amaap.ttp.creditcard.domain.model.exception;

public class InvalidCustomerNameException extends CustomerValidationException {
    public InvalidCustomerNameException(String message) {
        super(message);
    }
}

