package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.domain.model.entity.exception.customerexception.InvalidCustomerDataException;

public class CustomerService {
    public Customer create(String name, String email) throws InvalidCustomerDataException {
        return Customer.create(1,name,email);
    }
}
