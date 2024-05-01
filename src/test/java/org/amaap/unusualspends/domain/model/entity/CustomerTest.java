package org.amaap.unusualspends.domain.model.entity;

import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerEmailIdException;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerIdException;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void shouldAbleToThrowExceptionWhenCustomerIdIsInvalid() {
        assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(-1, "baburao Apte", "baburao@babu.com");

        });
        assertThrows(InvalidCustomerIdException.class, () -> {
            Customer.create(0, "baburao Apte", "baburao@babu.com");

        });
    }

    @Test
    void shouldAbleToThrowExceptionWhenCustomerNameIsInvalid() {
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, null, "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "BA", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "   Ba  A", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "Ba          ", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "Baburao", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "Baburao A", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "@Baburao Ap", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "Babur$ Apte", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "Baburao Apte%", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "$%^* Apte%", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            Customer.create(1, "4789Apte%", "baburao@babu.com");
        });
    }

    @Test
    void shouldAbleToThrowAnExceptionWhenCustomerEmailIdIsInvalid() {

        assertDoesNotThrow(() -> {
            Customer.create(1, "Baburao Apte", "baburao.apte@gmail.com");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.create(1, "Baburao Apte", null);
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.create(1, "Baburao Apte", "");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.create(1, "Baburao Apte", "invalid-email");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            Customer.create(1, "Baburao Apte", "456@456");
        });
    }

}