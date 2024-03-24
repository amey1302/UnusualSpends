package com.amaap.ttp.creditcard.domain;

import com.amaap.ttp.creditcard.domain.model.Category;
import com.amaap.ttp.creditcard.domain.model.CreditCard;
import com.amaap.ttp.creditcard.domain.model.Customer;
import com.amaap.ttp.creditcard.domain.model.Transaction;
import com.amaap.ttp.creditcard.domain.model.exception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionIdException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

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

    @Test
    void shouldAbleToCreateTransactionForTheCreditCard() throws InvalidCreditCardIdException, InvalidTransactionException {
        //arrange
        int creditCardId = 1;
        CreditCard creditCard = CreditCard.create(creditCardId);
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024,03,14);
        double amount = 234.29;
        Category category = Category.Groceries;
        Transaction expected = Transaction.create(transactionId, date, amount, category);

        //act
        creditCardManger.createTransactionForACreditCard(transactionId, date, amount, category, creditCard);

        //assert
        assertEquals(expected, creditCard.getTransaction());

    }

}