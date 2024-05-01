package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    CustomerService customerService = new CustomerService();
    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Customer expected = Customer.create(id,name,email);

        // act
        Customer actual = customerService.create(name,email);

        // assert
        assertEquals(expected,actual);
    }

}