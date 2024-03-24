package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.exception.creditcardexception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.model.CreditCard;
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