package org.amaap.unusualspends.repository.impl.db.impl;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakeDatabaseTest {
    private FakeDatabase fakeDatabase;

    @BeforeEach
    void setUp() {
        fakeDatabase = new FakeDatabase();

    }

    @Test
    void shouldBeABleToInsertCustomerDataIntoCustomerTable() throws InvalidCustomerDataException {
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
}