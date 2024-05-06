package org.amaap.unusualspends.domain.model.entity;

import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;

import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String email;
    private CreditCard creditCard;

    private Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Customer create(int id, String name, String email) throws InvalidCustomerDataException {
        return new Customer(id, name, email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }
}
