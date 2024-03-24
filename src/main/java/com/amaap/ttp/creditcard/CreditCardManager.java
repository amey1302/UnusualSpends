package com.amaap.ttp.creditcard;

import com.amaap.ttp.creditcard.domain.model.Category;
import com.amaap.ttp.creditcard.domain.model.CreditCard;
import com.amaap.ttp.creditcard.domain.model.Customer;
import com.amaap.ttp.creditcard.domain.model.Transaction;
import com.amaap.ttp.creditcard.domain.exception.customerexception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.exception.creditcardexception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;

import java.time.LocalDate;

public class CreditCardManager {
    public Customer createCustomer(int customerId, String customerName, String customerEmail) throws CustomerValidationException {
        // Create a customer using the provided data
        Customer customer = Customer.createCustomer(customerId, customerName, customerEmail);
        return customer;
    }

    public void createCreditCardForCustomer(int creditCardId, Customer customer) throws InvalidCreditCardIdException {
        CreditCard creditCard = CreditCard.create(creditCardId);
        customer.setCreditCard(creditCard);
    }

    public void createTransactionForACreditCard(int transactionId, LocalDate date, double amount, Category category, CreditCard creditCard) throws InvalidTransactionException {
        Transaction transaction = Transaction.create(transactionId,date,amount,category);
        creditCard.addTransaction(transaction);
    }


}
