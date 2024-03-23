package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.model.exception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidCustomerIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {
    @Test
    void shouldAbleToValidateCreditCardId(){
        //Arrange, Act and Assert
        assertThrows(InvalidCreditCardIdException.class, () -> {
           CreditCard.create(-1);

        });

        assertThrows(InvalidCreditCardIdException.class, () -> {
            CreditCard.create(0);

        });
    }

}