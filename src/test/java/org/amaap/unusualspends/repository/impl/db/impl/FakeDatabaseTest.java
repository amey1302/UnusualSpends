package org.amaap.unusualspends.repository.impl.db.impl;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.Transaction;
import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakeDatabaseTest {
    private FakeDatabase fakeDatabase;

    @BeforeEach
    void setUp() {
        fakeDatabase = new FakeDatabase();

    }

    @Test
    void shouldBeAbleToInsertCustomerDataIntoCustomerTable() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Customer expected = Customer.create(id, name, email);

        // act
        Customer actual = fakeDatabase.insertIntoCustomerTable(expected);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToUpdateCustomerDataIntoCustomerTableWhenCreditCardIsAssignedToCustomer() throws InvalidCustomerDataException, InvalidCardIdException {
        // arrange
        int id = 1;
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Customer customer = Customer.create(id, name, email);
        CreditCard creditCard = CreditCard.create(1);
        customer.setCreditCard(creditCard);

        // act
        fakeDatabase.insertIntoCustomerTable(customer);
        customer.setCreditCard(creditCard);
        fakeDatabase.updateCustomerTable(customer);

        // assert
        Customer updatedCustomer = fakeDatabase.customerList.get(0);
        assertEquals(customer, updatedCustomer);
    }

    @Test
    void shouldBeAbleToInsertCreditCardDataIntoCreditCardTable() throws InvalidCardIdException {
        // arrange
        int id = 1;
        CreditCard expected = CreditCard.create(id);

        // act
        CreditCard actual = fakeDatabase.insertIntoCreditCardTable(expected);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToInsertTransactionDataIntoTransactionTable() throws InvalidCardIdException {
        // arrange
        int transactionId = 1;
        LocalDate date = LocalDate.of(2024, 05, 23);
        double amount = 200.0;
        String category = "Travel";
        Transaction expected = Transaction.create(transactionId, date, amount, category);

        // act
        Transaction actual = fakeDatabase.insertIntoTransactionTable(expected);

        // assert
        assertEquals(expected, actual);
    }
}