package org.amaap.unusualspends.service;

import org.amaap.unusualspends.repository.TransactionRepository;
import org.amaap.unusualspends.repository.impl.InMemoryTransactionRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.amaap.unusualspends.service.exception.transactionexception.InvalidTransactionAmountException;
import org.amaap.unusualspends.service.exception.transactionexception.InvalidTransactionCategoryException;
import org.amaap.unusualspends.service.exception.transactionexception.InvalidTransactionIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionServiceTest {
    private TransactionService transactionService;
    private TransactionRepository transactionRepository;
    private InMemoryDatabase inMemoryDatabase;

    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        transactionRepository = new InMemoryTransactionRepository(inMemoryDatabase);
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionId() {
        // arrange
        int invalidTransactionId = -1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String category = "BOOKS";


        // assert
        assertThrows(InvalidTransactionIdException.class, () ->
                transactionService.create(invalidTransactionId, date, amount, category));
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionAmount() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double invalidAmount = -150.0;
        String category = "TRAVEL";

        // assert
        assertThrows(InvalidTransactionAmountException.class, () ->
                transactionService.create(transactionId, date, invalidAmount, category));
    }


    @Test
    void shouldThrowExceptionForInvalidTransactionCategoryWithDigit() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String invalidCategory = "123";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, invalidCategory));
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionCategoryWithSpaces() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String invalidCategory = "Invalid Category";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, invalidCategory));
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionCategoryNotFound() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String invalidCategory = "InvalidCategory";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, invalidCategory));
    }

    @Test
    void shouldThrowExceptionForEmptyTransactionCategory() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String emptyCategory = "";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, emptyCategory));
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionCategoryWithLeadingSpaces() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String invalidCategory = " CATEGORY1";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, invalidCategory));
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionCategoryWithTrailingSpaces() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String invalidCategory = "CATEGORY1 ";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, invalidCategory));
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionCategoryWithTabs() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String invalidCategory = "CATEGORY\t1";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, invalidCategory));
    }


    @Test
    void shouldBeAbleToCreateTransactionWithValidCategoryGroceries() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String validCategory = "TRAVEL";

        // assert
        assertDoesNotThrow(() ->
                transactionService.create(transactionId, date, amount, validCategory));
    }

    @Test
    void shouldBeAbleToCreateTransactionWithValidCategoryTravel() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String validCategory = "TRAVEL";

        // assert
        assertDoesNotThrow(() ->
                transactionService.create(transactionId, date, amount, validCategory));
    }

    @Test
    void shouldThrowExceptionForInvalidTransactionCategoryNotInEnum() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2023, 5, 15);
        double amount = 150.0;
        String invalidCategory = "INVALID_CATEGORY";

        // assert
        assertThrows(InvalidTransactionCategoryException.class, () ->
                transactionService.create(transactionId, date, amount, invalidCategory));
    }
}
