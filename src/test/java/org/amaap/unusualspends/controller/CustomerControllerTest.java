package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.amaap.unusualspends.repository.CustomerRepository;
import org.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.amaap.unusualspends.service.CustomerService;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;
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

    @Test
    void shouldBeAbleToReturnBAD_REQUESTIfCustomerWithInvalidDataCreated() {
        // arrange
        String name = "89";
        String email = "abc@gmail.co,m";
        Response expected = new Response(HttpStatus.BAD_REQUEST, "Invalid CustomerName : 89");

        // act
        Response actual = customerController.create(name, email);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void shouldBeAbleToAssignCreditCardToCustomer() throws InvalidCardIdException, InvalidCustomerDataException {
        // arrange
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Customer customer = customerService.create(name, email);
        CreditCard creditCard = CreditCard.create(1);
        Response expected = new Response(HttpStatus.OK, "Customer Assigned with credit card!");

        // act
        Response actual = customerController.assignCard(creditCard, customer);

        // assert
        assertEquals(expected, actual);
    }
}
