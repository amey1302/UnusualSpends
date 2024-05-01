package org.amaap.unusualspends.domain.model.entity;

import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerDataException;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerEmailIdException;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerIdException;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerNameException;

import java.util.Objects;

import static org.amaap.unusualspends.domain.model.entity.exception.validator.CustomerEmailValidator.isInvalidEmailId;
import static org.amaap.unusualspends.domain.model.entity.exception.validator.CustomerNameValidator.isInValidCustomerName;

public class Customer {
    private int id;
    private String name;
    private String email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Customer create(int id, String name, String email) throws InvalidCustomerDataException {
        if (id <= 0) throw new InvalidCustomerIdException("Invalid CustomerId : " + id);
        if (isInValidCustomerName(name)) throw new InvalidCustomerNameException("Invalid CustomerName : " + name);
        if (isInvalidEmailId(email)) throw new InvalidCustomerEmailIdException("Invalid Customer Email : " + email);
        return new Customer(id, name, email);
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
