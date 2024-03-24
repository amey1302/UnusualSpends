package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        assertEquals(expectedTotal,UnusualSpend.getTotalAmountSpent());

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
        assertEquals(2,currentMonthTransactions.size());

    }

}