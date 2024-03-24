package com.amaap.ttp.creditcard;

import com.amaap.ttp.creditcard.domain.model.Category;
import com.amaap.ttp.creditcard.domain.model.CreditCard;
import com.amaap.ttp.creditcard.domain.model.Customer;
import com.amaap.ttp.creditcard.domain.model.Transaction;
import com.amaap.ttp.creditcard.domain.exception.customerexception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.exception.creditcardexception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

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
        List<Transaction> expected = List.of(Transaction.create(transactionId, date, amount, category));

        //act
        creditCardManger.createTransactionForACreditCard(transactionId, date, amount, category, creditCard);

        //assert
        assertEquals(expected, creditCard.getTransactions());

    }
    @Test
    void shouldAbleToAddMultipleTransactionsForACreditCard() throws InvalidCreditCardIdException, InvalidTransactionException {
        //arrange
        int creditCardId = 1;
        CreditCard creditCard = CreditCard.create(creditCardId);
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024,03,14);
        double amount = 234.29;
        Category category = Category.Groceries;
        List<Transaction> expected = List.of(Transaction.create(transactionId, date, amount, category),
                Transaction.create(2, date, 540.29, Category.Travel));

        //act
        creditCardManger.createTransactionForACreditCard(transactionId, date, amount, category, creditCard);
        creditCardManger.createTransactionForACreditCard(2, date, 540.29, Category.Travel, creditCard);

        //assert
        assertEquals(expected, creditCard.getTransactions());

    }

}