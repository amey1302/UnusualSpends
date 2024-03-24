package com.amaap.ttp.creditcard;

import com.amaap.ttp.creditcard.domain.model.*;
import com.amaap.ttp.creditcard.domain.exception.customerexception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.exception.creditcardexception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardManagerTest {
    CreditCardManager creditCardManager =  new CreditCardManager();

    @Test
    void shouldAbleToCreateCustomer() throws CustomerValidationException {
        //arrange
        int customerId = 1;
        String customerName = "Rohit Sharma";
        String customerEmailId = "rohit@mi.com";
        Customer expected = Customer.createCustomer(customerId, customerName, customerEmailId);

        //act
        Customer actual = creditCardManager.createCustomer(customerId, customerName, customerEmailId);
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
        creditCardManager.createCreditCardForCustomer(creditCardId, customer);

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
        creditCardManager.createTransactionForACreditCard(transactionId, date, amount, category, creditCard);

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
        creditCardManager.createTransactionForACreditCard(transactionId, date, amount, category, creditCard);
        creditCardManager.createTransactionForACreditCard(2, date, 540.29, Category.Travel, creditCard);

        //assert
        assertEquals(expected, creditCard.getTransactions());

    }
    @Test
    void shouldAbleToCalculateUnusualSpendsForACustomer() throws CustomerValidationException, InvalidTransactionException {
        //Arrange
        //customer
        int customerId = 1;
        String customerName = "Rohit Sharma";
        String customerEmailId = "rohit@sharma.com";
        Customer customer = Customer.createCustomer(customerId, customerName, customerEmailId);
        CreditCard creditCard = new CreditCard(1);

        //multiple transactions for same customer
        List<Transaction> transactions = List.of(
                Transaction.create(1, LocalDate.of(2024,03,14), 234.29, Category.Groceries),
                Transaction.create(2, LocalDate.of(2024,02,15), 540.29, Category.Travel));
        creditCard.setTransactions(transactions);
        customer.setCreditCard(creditCard);

//        CategoryWithAmount categoryWithAmount1 = new CategoryWithAmount(Category.Travel,540.29);
//        CategoryWithAmount categoryWithAmount2 = new CategoryWithAmount(Category.Groceries,234.29);
//        UnusualSpend expected = new UnusualSpend(customer,List.of(categoryWithAmount1,categoryWithAmount2));

        //Act
        boolean actual = creditCardManager.analyzeUnusualSpendsFor(creditCard);

        //Assert
        assertTrue(actual);
    }
    @Test
    void shouldAbleToSendEmailForACustomerWithUnusualSpends(){
        //unusual spends
        //emailNotifier to send the unusual spends data
        //ccm has a email notifier
    }

}