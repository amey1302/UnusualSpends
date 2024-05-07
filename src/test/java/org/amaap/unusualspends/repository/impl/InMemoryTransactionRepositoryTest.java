package org.amaap.unusualspends.repository.impl;

import org.amaap.unusualspends.domain.model.entity.Transaction;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryTransactionRepositoryTest {
    private InMemoryTransactionRepository transactionRepository;
    private InMemoryDatabase inMemoryDatabase;

    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        transactionRepository = new InMemoryTransactionRepository(inMemoryDatabase);
    }

    @Test
    void shouldBeAbleToInsertTransactionIntoTransactionTable() {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024, 05, 23);
        double amount = 200.0;
        String category = "Travel";
        Transaction expected = Transaction.create(transactionId, date, amount, category);


        // act
        Transaction insertedTransaction = transactionRepository.insert(expected);

        // assert
        assertEquals(expected, insertedTransaction);
    }


}