package com.amaap.ttp.creditcard.domain;

import com.amaap.ttp.creditcard.domain.model.CreditCard;
import com.amaap.ttp.creditcard.domain.model.Customer;
import com.amaap.ttp.creditcard.domain.model.exception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidCreditCardIdException;

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
}
