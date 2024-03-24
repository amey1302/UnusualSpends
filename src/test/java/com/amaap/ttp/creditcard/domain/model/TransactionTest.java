package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionIdException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

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


}