package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerDataException;
import org.amaap.unusualspends.repository.CustomerRepository;
import org.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerServiceTest {
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    private InMemoryDatabase inMemoryDatabase;


    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
        customerService = new CustomerService(customerRepository);
    }


    @Test
    void shouldBeAbleToCreateCustomer() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Customer expected = Customer.create(id, name, email);

        // act
        Customer actual = customerService.create(name, email);

        // assert
        assertEquals(expected, actual);
    }

}