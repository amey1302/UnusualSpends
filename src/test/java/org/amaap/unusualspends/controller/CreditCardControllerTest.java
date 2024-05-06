package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.repository.CreditCardRepository;
import org.amaap.unusualspends.repository.impl.InMemoryCreditCardRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.amaap.unusualspends.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditCardControllerTest {
    private InMemoryDatabase inMemoryDatabase;
    private CreditCardRepository creditCardRepository;
    private CreditCardService creditCardService;
    private CreditCardController creditCardController;

    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        creditCardRepository = new InMemoryCreditCardRepository(inMemoryDatabase);
        creditCardService = new CreditCardService(creditCardRepository);
        creditCardController = new CreditCardController(creditCardService);
    }

    @Test
    void shouldBeAbleGetOKResponseWhenCreditCardCreated() {
        // arrange
        Response expected = new Response(HttpStatus.OK, "CreditCard Created Successfully!");

        // act
        Response actual = creditCardController.create();

        // assert
        assertEquals(expected, actual);
    }

}