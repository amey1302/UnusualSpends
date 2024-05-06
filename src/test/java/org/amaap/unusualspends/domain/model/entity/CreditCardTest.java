package org.amaap.unusualspends.domain.model.entity;

import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.junit.jupiter.api.Test;

class CreditCardTest {
    private Customer customer;

    @Test
    void shouldBeAbleToCreateCreditCard() throws InvalidCardIdException {
        // arrange
        int id = 1;

        // act
        CreditCard actual = CreditCard.create(id);

        // assert

    }

}