package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.repository.CustomerRepository;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerEmailIdException;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerNameException;

import static org.amaap.unusualspends.domain.model.entity.exception.validator.CustomerEmailValidator.isInvalidEmailId;
import static org.amaap.unusualspends.domain.model.entity.exception.validator.CustomerNameValidator.isInValidCustomerName;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(String name, String email) throws InvalidCustomerDataException {
        if (isInValidCustomerName(name)) throw new InvalidCustomerNameException("Invalid CustomerName : " + name);
        if (isInvalidEmailId(email)) throw new InvalidCustomerEmailIdException("Invalid Customer Email : " + email);
        Customer customer = Customer.create(1, name, email);
        return customerRepository.insert(customer);
    }

    public void assignCard(CreditCard creditCard, Customer customer) {
        customer.setCreditCard(creditCard);
        customerRepository.update(customer);
    }
}
