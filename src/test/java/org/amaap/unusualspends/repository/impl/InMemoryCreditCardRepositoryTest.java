package org.amaap.unusualspends.repository.impl;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryCreditCardRepositoryTest {
    private InMemoryCreditCardRepository creditCardRepository;
    private InMemoryDatabase inMemoryDatabase;

    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
    }
    @Test
    void shouldBeAbleToInsertCreditCardIntoTable() throws InvalidCardIdException {
        // arrange
        int id = 1;

        // act
        CreditCard creditCard = CreditCard.create(id);
        CreditCard insertedCreditCard = creditCardRepository.insert(creditCard);

        // assert
        assertEquals(creditCard, insertedCreditCard);
    }

}