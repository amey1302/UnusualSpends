package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionAmountException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionIdException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    @Test
    void shouldBeAbleToThrowAnExceptionWhenTransactionIdIsLessThanEqualToZero() {
        //Arrange
        int transactionId = 0;
        LocalDate date = LocalDate.of(2024, 12, 22);
        double amount = 2000.0;
        Category category = Category.Travel;
        //Act and Assert
        assertThrows(InvalidTransactionIdException.class, () -> {
            Transaction.create(transactionId, date, amount, category);
        });

    }

    @Test
    void shouldBeAbleToThrowAnExceptionWhenTransactionAmountIsZero() {
        //Arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024, 12, 22);
        double amount = 0;
        Category category = Category.Groceries;

        //Act and Assert
        assertThrows(InvalidTransactionAmountException.class, () -> {
            Transaction.create(transactionId, date, amount, category);
        });

    }

    @Test
    void shouldBeAbleToThrowAnExceptionWhenTransactionAmountIsNegative() {
        //Arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024, 12, 22);
        double amount = -1;
        Category category = Category.Travel;


        //Act and Assert
        assertThrows(InvalidTransactionAmountException.class, () -> {
            Transaction.create(transactionId, date, amount, category);
        });
    }

    @Test
    void shouldBeAbleToThrowAnExceptionWhenTransactionAmountIsLessThanHundred() {
        //Arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024, 12, 22);
        double amount = 98;
        Category category = Category.Travel;

        //Act and Assert
        assertThrows(InvalidTransactionAmountException.class, () -> {
            Transaction.create(transactionId, date, amount, category);
        });
    }

    //Transaction Date Validation
    @Test
    void shouldNotThrowAnExceptionWhenTransactionDateIsValid() {
        //Arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024, 12, 22);
        double amount = 1008.0;
        Category category = Category.Travel;

        //Act and Assert
        assertDoesNotThrow(() -> {
            Transaction.create(transactionId, date, amount, category);
        });
    }


}