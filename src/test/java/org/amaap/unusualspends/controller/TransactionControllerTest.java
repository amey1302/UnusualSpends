package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.repository.TransactionRepository;
import org.amaap.unusualspends.repository.impl.InMemoryTransactionRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.amaap.unusualspends.service.TransactionService;
import org.amaap.unusualspends.service.exception.transactionexception.InvalidTransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionControllerTest {
    private TransactionController transactionController;
    private TransactionService transactionService;
    private TransactionRepository transactionRepository;
    private InMemoryDatabase inMemoryDatabase;

    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        transactionRepository = new InMemoryTransactionRepository(inMemoryDatabase);
        transactionService = new TransactionService(transactionRepository);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    void shouldBeAbleToReturnOkIfTransactionIsPerformed() throws InvalidTransactionException {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.now();
        double amount = 244.50;
        String category = "TRAVEL";
        Response expected = new Response(HttpStatus.OK, "Transaction Performed");

        // act
        Response actual = transactionController.create(transactionId, date, amount, category);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToReturnBAD_REQUESTIfInvalidAmountIsPassed() throws InvalidTransactionException {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.now();
        double amount = -244.50;
        String category = "TRAVEL";
        Response expected = new Response(HttpStatus.BAD_REQUEST, "Invalid Transaction Details");

        // act
        Response actual = transactionController.create(transactionId, date, amount, category);

        // assert
        assertEquals(expected, actual);
    }

}