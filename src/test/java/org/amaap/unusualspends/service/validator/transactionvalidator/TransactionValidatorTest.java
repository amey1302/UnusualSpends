package org.amaap.unusualspends.service.validator.transactionvalidator;

import org.amaap.unusualspends.service.exception.transactionexception.InvalidTransactionDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionValidatorTest {

    @Test
    void shouldBeAbleToReturnFalseForValidTransactionDate() throws InvalidTransactionDateException {
        // Arrange
        LocalDate validDate = LocalDate.of(2023, 5, 20);

        // Act
        boolean result = TransactionValidator.isInvalidTransactionDate(validDate);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldBeAbleToReturnTrueForInvalidTransactionAmount() {
        // Arrange
        double invalidAmount = -100.0;

        // Act
        boolean result = TransactionValidator.isInvalidTransactionAmount(invalidAmount);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldBeAbleToReturnTrueForValidTransactionAmount() {
        // Arrange
        double validAmount = 150.0;

        // Act
        boolean result = TransactionValidator.isValidTransactionAmount(validAmount);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldBeAbleToReturnTrueForInvalidTransactionCategoryWithDigit() {
        // Arrange
        String category = "CATEGORY1";

        // Act
        boolean result = TransactionValidator.isInvalidTransactionCategory(category);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldBeAbleToReturnTrueForInvalidTransactionCategoryWithSpace() {
        // Arrange
        String category = "CATEGORY WITH SPACE";

        // Act
        boolean result = TransactionValidator.isInvalidTransactionCategory(category);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldBeAbleToReturnTrueForInvalidTransactionCategoryNotFound() {
        // Arrange
        String category = "UNKNOWN_CATEGORY";

        // Act
        boolean result = TransactionValidator.isInvalidTransactionCategory(category);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldBeAbleToReturnTrueForValidTransactionCategory() {
        // Arrange
        String category = "GROCERIES";

        // Act
        boolean result = TransactionValidator.isValidTransactionCategory(category);

        // Assert
        assertTrue(result);
    }
}
