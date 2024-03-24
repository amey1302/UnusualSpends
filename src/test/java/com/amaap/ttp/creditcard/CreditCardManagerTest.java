package com.amaap.ttp.creditcard;

import com.amaap.ttp.creditcard.domain.DTO.UnusualSpendingEmailAlertDTO;
import com.amaap.ttp.creditcard.domain.exception.creditcardexception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.exception.customerexception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;
import com.amaap.ttp.creditcard.domain.model.*;
import com.amaap.ttp.creditcard.domain.services.EmailAlert;
import com.amaap.ttp.creditcard.domain.services.EmailAlertMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardManagerTest {

    private CreditCardManager creditCardManager;
    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        creditCardManager = new CreditCardManager();
        transactions = new ArrayList<>();
    }

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
        LocalDate date = LocalDate.of(2024, 03, 14);
        double amount = 234.29;
        Category category = Category.Groceries;
        List<Transaction> expected = new ArrayList<>();
        expected.add(Transaction.create(transactionId, date, amount, category));

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
        LocalDate date = LocalDate.of(2024, 03, 14);
        double amount = 234.29;
        Category category = Category.Groceries;
        List<Transaction> expected = new ArrayList<>();
        expected.add(Transaction.create(transactionId, date, amount, category));
        expected.add(Transaction.create(2, date, 540.29, Category.Travel));

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
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.create(1, LocalDate.of(2024, 03, 14), 234.29, Category.Groceries));
        transactions.add(Transaction.create(2, LocalDate.of(2024, 02, 15), 540.29, Category.Travel));
        creditCard.setTransactions(transactions);
        customer.setCreditCard(creditCard);
        //Act
        boolean actual = creditCardManager.analyzeUnusualSpendsFor(creditCard);

        //Assert
        assertTrue(actual);
    }
    @Test
    void shouldAbleToSendEmailForACustomerWithUnusualSpends() throws CustomerValidationException, InvalidTransactionException {
        int customerId = 1;
        String customerName = "Amey Kulkarni";
        String customerEmailId = "ameykulkarni1302@gmail.com";
        Customer customer = Customer.createCustomer(customerId, customerName, customerEmailId);
        CreditCard creditCard = new CreditCard(1);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.create(1, LocalDate.of(2024, 02, 24), 148.0, Category.Groceries));
        transactions.add(Transaction.create(2, LocalDate.of(2024, 02, 15), 100.0, Category.Travel));
        transactions.add(Transaction.create(3, LocalDate.of(2024, 03, 25), 928.0, Category.Groceries));
        transactions.add(Transaction.create(4, LocalDate.of(2024, 03, 15), 500.0, Category.Travel));
        creditCard.setTransactions(transactions);
        customer.setCreditCard(creditCard);

        Map<Category, Double> unusualSpends = UnusualSpend.calculateUnusualSpend(
                UnusualSpend.currentMonthTransactions(transactions),
                UnusualSpend.previousMonthTransactions(transactions),40.0
        );
        double totalUnusualAmount = UnusualSpend.totalUnusualAmountSpend(unusualSpends);

        // Create DTO
        UnusualSpendingEmailAlertDTO emailDTO = new UnusualSpendingEmailAlertDTO(customer, unusualSpends, totalUnusualAmount);
        emailDTO.setCustomer(customer);
        emailDTO.setUnusualSpends(unusualSpends);
        emailDTO.setTotalUnusualAmount(totalUnusualAmount);

        // Act
        EmailAlert emailAlert = new EmailAlert();
        String subject = "Unusual spending alert"; // Using from EmailAlertMessage
        String body = EmailAlertMessage.generateUnusualSpendingEmail(emailDTO); // Dummy body
        String result = emailAlert.sendEmail(subject, body, customerEmailId);

        // Assert
        assertEquals("Email Sent Successfully", result);
    }
    @Test
    void shouldAbleToSendEmailForACustomerWithUnusualSpendsWIthThresholdPercentage() throws CustomerValidationException, InvalidTransactionException {
        int customerId = 1;
        String customerName = "Amey Kulkarni";
        String customerEmailId = "ameykulkarni1302@gmail.com";
        Customer customer = Customer.createCustomer(customerId, customerName, customerEmailId);
        CreditCard creditCard = new CreditCard(1);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(Transaction.create(1, LocalDate.of(2024, 02, 24), 148.0, Category.Groceries));
        transactions.add(Transaction.create(2, LocalDate.of(2024, 02, 15), 100.0, Category.Travel));
        transactions.add(Transaction.create(3, LocalDate.of(2024, 03, 25), 928.0, Category.Groceries));
        transactions.add(Transaction.create(4, LocalDate.of(2024, 03, 15), 500.0, Category.Travel));
        creditCard.setTransactions(transactions);
        customer.setCreditCard(creditCard);

        Map<Category, Double> unusualSpends = UnusualSpend.calculateUnusualSpend(
                UnusualSpend.currentMonthTransactions(transactions),
                UnusualSpend.previousMonthTransactions(transactions),40.0
        );
        double totalUnusualAmount = UnusualSpend.totalUnusualAmountSpend(unusualSpends);

        // Create DTO
        UnusualSpendingEmailAlertDTO emailDTO = new UnusualSpendingEmailAlertDTO(customer, unusualSpends, totalUnusualAmount);
        emailDTO.setCustomer(customer);
        emailDTO.setUnusualSpends(unusualSpends);
        emailDTO.setTotalUnusualAmount(totalUnusualAmount);

        // Act
        EmailAlert emailAlert = new EmailAlert();
        String subject = "Unusual spending alert"; // Using from EmailAlertMessage
        String body = EmailAlertMessage.generateUnusualSpendingEmail(emailDTO); // Dummy body
        String result = emailAlert.sendEmail(subject, body, customerEmailId);

        // Assert
        assertEquals("Email Sent Successfully", result);
    }

}
