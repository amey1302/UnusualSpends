package org.amaap.unusualspends.repository.impl.db;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.Transaction;

public interface InMemoryDatabase {
    Customer insertIntoCustomerTable(Customer customer);

    CreditCard insertIntoCreditCardTable(CreditCard creditCard);

    void updateCustomerTable(Customer customer);

    Transaction insertIntoTransactionTable(Transaction transaction);
}
