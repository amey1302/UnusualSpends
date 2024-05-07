package org.amaap.unusualspends.domain.model.entity;

import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerTest {
    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";

        // act
        Customer customer = Customer.create(id, name, email);

        // assert
        assertNotNull(customer);
    }
}