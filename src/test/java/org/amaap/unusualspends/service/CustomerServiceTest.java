package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.amaap.unusualspends.repository.CustomerRepository;
import org.amaap.unusualspends.repository.impl.InMemoryCustomerRepository;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerEmailIdException;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldAbleToThrowExceptionWhenCustomerNameIsInvalid() {
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create(null, "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("BA", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("   Ba  A", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("Ba          ", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("Baburao", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("Baburao A", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("@Baburao Ap", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("Babur$ Apte", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("Baburao Apte%", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("$%^* Apte%", "baburao@babu.com");
        });
        assertThrows(InvalidCustomerNameException.class, () -> {
            customerService.create("4789Apte%", "baburao@babu.com");
        });
    }

    @Test
    void shouldAbleToThrowAnExceptionWhenCustomerEmailIdIsInvalid() {

        assertDoesNotThrow(() -> {
            customerService.create("Baburao Apte", "baburao.apte@gmail.com");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            customerService.create("Baburao Apte", null);
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            customerService.create("Baburao Apte", "");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            customerService.create("Baburao Apte", "invalid-email");
        });
        assertThrows(InvalidCustomerEmailIdException.class, () -> {
            customerService.create("Baburao Apte", "456@456");
        });
    }

    @Test
    void shouldBeAbleToAssignCardToCustomer() throws InvalidCustomerDataException, InvalidCardIdException {
        // arrange
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Customer customer = Customer.create(1, name, email);
        CreditCard creditCard = CreditCard.create(1);

        // act
        customerService.assignCard(creditCard, customer);

        // assert
        assertEquals(creditCard, customer.getCreditCard());
    }

}