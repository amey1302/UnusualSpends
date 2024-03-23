package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.model.exception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidCustomerEmailIdException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidCustomerIdException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidCustomerNameException;

import java.util.Objects;

import static com.amaap.ttp.creditcard.domain.validator.CustomerValidation.isCustomerEmailIdValid;
import static com.amaap.ttp.creditcard.domain.validator.CustomerValidation.isCustomerNameValid;

public class Customer {
    private static int id;
    private static String name;
    private static String email;


    public static Customer createCustomer(int id, String name, String email) throws CustomerValidationException {
        if (id <= 0)
            throw new InvalidCustomerIdException("Invalid CustomerId : " + id);
        if (!isCustomerNameValid(name))
            throw new InvalidCustomerNameException("Invalid CustomerName : " + name);
        if (!isCustomerEmailIdValid(email))
            throw new InvalidCustomerEmailIdException("Invalid Customer Email : " + email);
        return new Customer(id, name, email);


    }

    private Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
