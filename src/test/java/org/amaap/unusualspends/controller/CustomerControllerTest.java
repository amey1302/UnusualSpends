package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.repository.CustomerRepository;
import org.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.amaap.unusualspends.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerControllerTest {
    private CustomerController customerController;
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private InMemoryDatabase inMemoryDatabase;
    @BeforeEach
    void setUp() {
        inMemoryDatabase = new FakeDatabase();
        customerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
        customerService = new CustomerService(customerRepository);
        customerController = new CustomerController(customerService);
    }

    @Test
    void shouldBeABleToReturnOkIfCustomerCreated() {
        // arrange
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Response expected = new Response(HttpStatus.OK, "Customer Created Successfully!");

        // act
        Response actual = customerController.create(name, email);

        // assert
        assertEquals(expected, actual);
    }
}
