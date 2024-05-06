package org.amaap.unusualspends.repository.impl.db;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;

public interface InMemoryDatabase {
    Customer insertIntoCustomerTable(Customer customer);

    CreditCard insertIntoCreditCardTable(CreditCard creditCard);

    void updateCustomerTable(Customer customer);
}
