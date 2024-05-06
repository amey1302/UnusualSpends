package org.amaap.unusualspends.repository.impl;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.repository.CreditCardRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;

public class InMemoryCreditCardRepository implements CreditCardRepository {
    private InMemoryDatabase inMemoryDatabase;

    public InMemoryCreditCardRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }


    @Override
    public CreditCard insert(CreditCard creditCard) {
        return inMemoryDatabase.insertIntoCreditCardTable(creditCard);
    }
}
