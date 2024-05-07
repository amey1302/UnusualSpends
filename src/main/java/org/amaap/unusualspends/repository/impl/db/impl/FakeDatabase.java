package org.amaap.unusualspends.repository.impl.db.impl;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.Transaction;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase implements InMemoryDatabase {
    List<Customer> customerList = new ArrayList<>();
    List<CreditCard> creditCardList = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();
    int customerIdCounter = 1;
    int creditCardIdCounter = 1;
    int transactionIdCounter = 1;

    @Override
    public Customer insertIntoCustomerTable(Customer customer) {
        customer.setId(customerIdCounter);
        customerList.add(customer);
        return customer;
    }

    @Override
    public CreditCard insertIntoCreditCardTable(CreditCard creditCard) {
        creditCard.setId(creditCardIdCounter);
        creditCardList.add(creditCard);
        return creditCard;
    }

    @Override
    public void updateCustomerTable(Customer customer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId() == customer.getId()) {
                customerList.set(i, customer);
                break;
            }
        }
    }

    @Override
    public Transaction insertIntoTransactionTable(Transaction transaction) {
        transaction.setTransactionId(transactionIdCounter);
        transactionList.add(transaction);
        return transaction;
    }
}
