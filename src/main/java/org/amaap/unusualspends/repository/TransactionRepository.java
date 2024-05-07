package org.amaap.unusualspends.repository;

import org.amaap.unusualspends.domain.model.entity.Transaction;

public interface TransactionRepository {
    Transaction insert(Transaction transaction);
}
