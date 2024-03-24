package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnusualSpendTest {
    @Test
    void shouldAbleToGetTotalTransactionsAmount() throws InvalidTransactionException {
        //arrange
        CreditCard creditCard = new CreditCard(1);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.create(1, LocalDate.of(2024, 02, 13), 230.0, Category.Groceries));
        transactions.add(Transaction.create(1, LocalDate.of(2024, 03, 13), 450.0, Category.Groceries));
        transactions.add(Transaction.create(1, LocalDate.of(2024, 02, 13), 330.0, Category.Groceries));
        creditCard.setTransactions(transactions);
        //act
        double expectedTotal = 230.0 + 450.0 + 330.0;
        //assert
        assertEquals(expectedTotal, UnusualSpend.getTotalAmountSpent());

    }

    @Test
    void shouldAblToReturnCurrentMonthTransactions() throws InvalidTransactionException {
        //Arrange
        CreditCard creditCard = new CreditCard(1);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.create(1, LocalDate.of(2024, 03, 13), 230.0, Category.Groceries));
        transactions.add(Transaction.create(2, LocalDate.of(2024, 03, 14), 450.0, Category.Groceries));
        transactions.add(Transaction.create(3, LocalDate.of(2024, 02, 13), 330.0, Category.Groceries));
        creditCard.setTransactions(transactions);

        //Act
        List<Transaction> currentMonthTransactions = UnusualSpend.currentMonthTransactions(transactions);

        //System.out.println(currentMonthTransactions);

        //Assert
        assertEquals(2, currentMonthTransactions.size());

    }

    @Test
    void shouldAblToReturnCurrentPreviousMonthTransactions() throws InvalidTransactionException {
        //Arrange
        CreditCard creditCard = new CreditCard(1);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.create(1, LocalDate.of(2024, 03, 13), 230.0, Category.Groceries));
        transactions.add(Transaction.create(2, LocalDate.of(2024, 01, 14), 450.0, Category.Groceries));
        transactions.add(Transaction.create(3, LocalDate.of(2024, 02, 13), 330.0, Category.Groceries));
        transactions.add(Transaction.create(4, LocalDate.of(2024, 02, 13), 330.0, Category.Groceries));
        creditCard.setTransactions(transactions);

        //Act
        List<Transaction> previousMonthTransactions = UnusualSpend.previousMonthTransactions(transactions);

        //System.out.println(previousMonthTransactions);

        //Assert
        assertEquals(2, previousMonthTransactions.size());

    }

    @Test
    void shouldAbleToGiveUnusualSpendWithCategoryAndAmount() throws InvalidTransactionException {
        //Arrange
        CreditCard creditCard = new CreditCard(1);
        UnusualSpend unusualSpend = new UnusualSpend();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.create(1, LocalDate.of(2024, 03, 13), 730.0, Category.Groceries));
        transactions.add(Transaction.create(2, LocalDate.of(2024, 02, 14), 250.0, Category.Travel));
        transactions.add(Transaction.create(3, LocalDate.of(2024, 03, 13), 500.0, Category.Travel));
        transactions.add(Transaction.create(4, LocalDate.of(2024, 02, 13), 330.0, Category.Groceries));
        creditCard.setTransactions(transactions);

        //Act
        List<Transaction> currentMonthTransactions = UnusualSpend.currentMonthTransactions(creditCard.getTransactions());
        List<Transaction> prevMonthTransactions = UnusualSpend.previousMonthTransactions(creditCard.getTransactions());


        Map<Category, Double> actual = UnusualSpend.calculateUnusualSpend(currentMonthTransactions, prevMonthTransactions);

        // System.out.println(actual);
        // Assert
        Map<Category, Double> expected = new HashMap<>();
        expected.put(Category.Groceries, 400.0);
        expected.put(Category.Travel, 250.0);
        assertEquals(expected, actual);
    }




}


