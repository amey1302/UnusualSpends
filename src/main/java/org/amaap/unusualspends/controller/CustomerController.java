package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.Customer;
import org.amaap.unusualspends.service.CustomerService;
import org.amaap.unusualspends.service.exception.customerexception.InvalidCustomerDataException;


public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    Response create(String name, String email) {
        try {
            customerService.create(name, email);
            return new Response(HttpStatus.OK, "Customer Created Successfully!");
        } catch (InvalidCustomerDataException exception) {
            return new Response(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }

    public Response assignCard(CreditCard creditCard, Customer customer) {
        try {
            customerService.assignCard(creditCard, customer);
            return new Response(HttpStatus.OK, "Customer Assigned with credit card!");
        } catch (Exception exception) {
            return new Response(HttpStatus.Internal_Error, exception.getMessage());
        }
    }
}
