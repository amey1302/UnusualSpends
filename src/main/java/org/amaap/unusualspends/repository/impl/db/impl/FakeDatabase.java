package org.amaap.unusualspends.repository.impl.db.impl;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase implements InMemoryDatabase {
    List<Customer> customerList = new ArrayList<>();
    int idCounter = 1;
    @Override
    public Customer insertIntoCustomerTable(Customer customer) {
        customer.setId(idCounter);
        customerList.add(customer);
        return customer;
    }
}
