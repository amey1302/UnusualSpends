package com.amaap.ttp.creditcard.domain.model.exception;

public class InvalidCustomerIdException extends CustomerValidationException{


    public InvalidCustomerIdException(String message) {
        super(message);
    }
}
