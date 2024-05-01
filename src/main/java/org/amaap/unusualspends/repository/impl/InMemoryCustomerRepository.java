package org.amaap.unusualspends.repository.impl;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.repository.CustomerRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;

public class InMemoryCustomerRepository implements CustomerRepository {
    private InMemoryDatabase inMemoryDatabase;

    public InMemoryCustomerRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public Customer insert(Customer customer) {
        return inMemoryDatabase.insertIntoCustomerTable(customer);
    }
}
