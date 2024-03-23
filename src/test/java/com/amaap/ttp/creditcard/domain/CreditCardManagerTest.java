package com.amaap.ttp.creditcard.domain;

import com.amaap.ttp.creditcard.domain.model.CreditCard;
import com.amaap.ttp.creditcard.domain.model.Customer;
import com.amaap.ttp.creditcard.domain.model.exception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidCreditCardIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreditCardManagerTest {
    CreditCardManager creditCardManger = new CreditCardManager();

    @Test
    void shouldAbleToCreateCustomer() throws CustomerValidationException {
        //arrange
        int customerId = 1;
        String customerName = "Rohit Sharma";
        String customerEmailId = "rohit@mi.com";
        Customer expected = Customer.createCustomer(customerId, customerName, customerEmailId);

        //act
        Customer actual = creditCardManger.createCustomer(customerId, customerName, customerEmailId);
        //assert
        assertEquals(expected, actual);

    }

    @Test
    void shouldAbleToCreateACreditCardForACustomer() throws CustomerValidationException, InvalidCreditCardIdException {
        //arrange
        int customerId = 1;
        String customerName = "Rohit Sharma";
        String customerEmailId = "rohit@sharma.com";
        Customer customer = Customer.createCustomer(customerId, customerName, customerEmailId);
        int creditCardId = 1;
        CreditCard expected = new CreditCard(creditCardId);
        assertNull(customer.getCreditCard());

        //act
        creditCardManger.createCreditCardForCustomer(creditCardId, customer);

        //assert
        assertEquals(expected, customer.getCreditCard());

    }
    

}