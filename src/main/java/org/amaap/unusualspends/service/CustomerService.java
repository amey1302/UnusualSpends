package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerDataException;
import org.amaap.unusualspends.repository.CustomerRepository;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(String name, String email) throws InvalidCustomerDataException {
        return Customer.create(1,name,email);
    }
}
