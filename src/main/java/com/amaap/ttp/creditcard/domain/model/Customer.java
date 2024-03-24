package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.exception.customerexception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.exception.customerexception.InvalidCustomerEmailIdException;
import com.amaap.ttp.creditcard.domain.exception.customerexception.InvalidCustomerIdException;
import com.amaap.ttp.creditcard.domain.exception.customerexception.InvalidCustomerNameException;

import java.util.Objects;

import static com.amaap.ttp.creditcard.validator.CustomerEmailValidation.isInValidCustomerEmailId;
import static com.amaap.ttp.creditcard.validator.CustomerNameValidation.isInValidCustomerName;

public class Customer {
    private final int id;
    private final String name;
    private final String email;

    private CreditCard creditCard;

    public static Customer create(int id, String name, String email) throws CustomerValidationException {
        if (id <= 0)
            throw new InvalidCustomerIdException("Invalid CustomerId : " + id);
        if (isInValidCustomerName(name))
            throw new InvalidCustomerNameException("Invalid CustomerName : " + name);
        if (isInValidCustomerEmailId(email))
            throw new InvalidCustomerEmailIdException("Invalid Customer Email : " + email);
        return new Customer(id, name, email);
    }

    private Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
