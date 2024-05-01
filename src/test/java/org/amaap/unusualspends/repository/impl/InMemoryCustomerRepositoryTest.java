package org.amaap.unusualspends.repository.impl;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerDataException;
import org.amaap.unusualspends.repository.impl.db.InMemoryDatabase;
import org.amaap.unusualspends.repository.impl.db.impl.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCustomerRepositoryTest {
    private InMemoryDatabase inMemoryDatabase;

    private InMemoryCustomerRepository inMemoryCustomerRepository;
    @BeforeEach
    void setUp(){
        inMemoryDatabase = new FakeDatabase();
        inMemoryCustomerRepository = new InMemoryCustomerRepository(inMemoryDatabase);
    }
    @Test
    void shouldBeAbleToStoreCustomerDataInInMemoryRepository() throws InvalidCustomerDataException {
        // arrange
        int id = 1;
        String name = "Baburao Apte";
        String email = "baburao.apte@gmail.com";
        Customer expected = Customer.create(id,name,email);

        // act
        Customer actual = inMemoryCustomerRepository.insert(expected);

        // assert
        assertEquals(expected,actual);


    }

}