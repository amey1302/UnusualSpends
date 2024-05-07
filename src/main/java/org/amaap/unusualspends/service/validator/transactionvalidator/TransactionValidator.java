package org.amaap.unusualspends.service.validator.transactionvalidator;

import org.amaap.unusualspends.domain.model.valueobjects.TransactionCategory;
import org.amaap.unusualspends.service.exception.transactionexception.InvalidTransactionDateException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class TransactionValidator {
    public static boolean isInvalidTransactionDate(LocalDate date) throws InvalidTransactionDateException {
        try {
            LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
            return false;
        } catch (DateTimeException e) {
            throw new InvalidTransactionDateException("Invalid transaction date: " + date + " " + e.getMessage());
        }
    }

    public static boolean isInvalidTransactionAmount(double amount) {
        return !isValidTransactionAmount(amount);
    }

    public static boolean isValidTransactionAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidTransactionCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return false;
        }
        if (category.matches(".*\\d.*") || category.contains(" ")) {
            return false;
        }

        try {
            TransactionCategory.valueOf(category.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isInvalidTransactionCategory(String category) {
        return !isValidTransactionCategory(category);
    }
}
