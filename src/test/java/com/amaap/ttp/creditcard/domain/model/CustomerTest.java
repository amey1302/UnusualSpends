package com.amaap.ttp.creditcard.domain.model;


import com.amaap.ttp.creditcard.domain.exception.customerexception.InvalidCustomerEmailIdException;
import com.amaap.ttp.creditcard.domain.exception.customerexception.InvalidCustomerIdException;
import com.amaap.ttp.creditcard.domain.exception.customerexception.InvalidCustomerNameException;
import com.amaap.ttp.creditcard.domain.model.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {


    @Test
    void shouldAbleToThrowExceptionWhenCustomerIdIsInvalid() {
        assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.createCustomer(-1, "baburao Apte", "baburao@babu.com");

        });
        assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.createCustomer(0, "baburao Apte", "baburao@babu.com");

        });
    }

    @Test
    void shouldAbleToThrowExceptionWhenCustomerNameIsInvalid() {
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, null, "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "BA", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "   Ba  A", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "Ba          ", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "Baburao", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "Baburao A", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "@Baburao Ap", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "Babur$ Apte", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte%", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "$%^* Apte%", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "4789Apte%", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.createCustomer(1, "9786789089", "baburao@babu.com");
        });
    }

    @Test
    void shouldAbleToThrowAnExceptionWhenCustomerEmailIdIsInvalid() {
        // valid customer Email id

        assertDoesNotThrow(() -> {
            Customer.createCustomer(1, "Baburao Apte", "baburao@example.com");
        });
        assertDoesNotThrow(() -> {
            Customer.createCustomer(1, "Baburao Apte", "baburao_apte123@example.com");
        });
        assertDoesNotThrow(() -> {
            Customer.createCustomer(1, "Baburao Apte", "baburao.apte123@example.co.uk");
        });

        // Invalid Customer Email id
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", null);
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "invalid-email");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "@example.com");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "baburao@babu@com");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "baburao.apte@example");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "Baburao.apte     @.gmail.com");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "baburao.babu0@com...com.");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.createCustomer(1, "Baburao Apte", "456@456");
        });
    }
}
