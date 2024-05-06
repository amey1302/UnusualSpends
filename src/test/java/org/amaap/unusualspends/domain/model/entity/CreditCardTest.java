package org.amaap.unusualspends.domain.model.entity;

import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreditCardTest {
    private Customer customer;

    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCardIdException {
        // arrange
        int id = 1;

        // act
        CreditCard actual = CreditCard.create(id);

        // assert
        assertNotNull(actual);

    }

}