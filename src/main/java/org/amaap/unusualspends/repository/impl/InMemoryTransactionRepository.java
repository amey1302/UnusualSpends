package org.amaap.unusualspends.repository.impl;

import org.amaap.unusualspends.domain.model.entity.Transaction;
import org.amaap.unusualspends.repository.TransactionRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;

public class InMemoryTransactionRepository implements TransactionRepository {
    private InMemoryDatabase inMemoryDatabase;

    public InMemoryTransactionRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Transaction insert(Transaction transaction) {
        return inMemoryDatabase.insertIntoTransactionTable(transaction);
    }
}

