package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionAmountException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionDateException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionIdException;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

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

//    @Test
//    void shouldBeAbleToCreateTransactionWithDateInLeapYear() {
//        // Arrange
//        int transactionId = 1;
//        LocalDate date = LocalDate.of(202000, 112, 29); // Leap year
//        double amount = 2000.0;
//        Category category = Category.Travel;
//
//        // Act and Assert
//        assertDoesNotThrow(() -> {
//            Transaction.create(transactionId, date, amount, category);
//        });
//    }
//
//    @Test
//    void shouldBeAbleToThrowAnExceptionWhenTransactionDateIsNotValid() {
//        //Arrange
//        int transactionId = 1;
//        LocalDate date = LocalDate.of(2024, 2, 31);//Feb contain 29 days
//        double amount = 100;
//        Category category = Category.Travel;
//
//        //Act and Assert
//        Throwable exception = assertThrows(DateTimeException.class, () -> {
//            Transaction.create(transactionId, date, amount, category);
//        });
//
//
//    }
//
//    @Test
//    void shouldBeAbleToThrowExceptionForTransactionDateWithInvalidMonth() {
//        // Arrange
//        int transactionId = 1;
//        LocalDate invalidDate = LocalDate.of(2024, 13, 22); // Invalid month (13)
//        double amount = 100;
//        Category category = Category.Travel;
//
//        // Act and Assert
//        assertThrows(InvalidTransactionDateException.class, () -> {
//            Transaction.create(transactionId, invalidDate, amount, category);
//        });
//    }
//
//    @Test
//    void shouldBeAbleToThrowExceptionForTransactionDateWithInvalidDayOfMonth() {
//        // Arrange
//        int transactionId = 1;
//        LocalDate invalidDate = LocalDate.of(2024, 1, 32); // Invalid day of month (32)
//        double amount = 100;
//        Category category = Category.Travel;
//
//        // Act and Assert
//        assertThrows(InvalidTransactionDateException.class, () -> {
//            Transaction.create(transactionId, invalidDate, amount, category);
//        });
//    }
}