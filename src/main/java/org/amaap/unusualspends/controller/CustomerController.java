package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.service.CustomerService;

public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    Response create(String name, String email) {
        try {
            customerService.create(name,email);
            return new Response(HttpStatus.OK, "Customer Created Successfully!");
        } catch (Exception exception) {
            return new Response(HttpStatus.BAD_REQUEST,exception.getMessage());
        }
    }
}
