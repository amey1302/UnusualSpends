package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.amaap.unusualspends.repository.CreditCardRepository;
import org.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreditCardServiceTest {
    private InMemoryDatabase inMemoryDatabase;
    private CreditCardService creditCardService;
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
        creditCardService = new CreditCardService(creditCardRepository);
    }

    @Test
    void shouldBeABleToCreateCreditCard() throws InvalidCardIdException {
        // arrange
        int id = 1;

        // act
        CreditCard actual = creditCardService.create();

        // assert
        assertNotNull(actual);
    }

}