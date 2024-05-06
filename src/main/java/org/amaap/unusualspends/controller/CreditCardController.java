package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.service.CreditCardService;

public class CreditCardController {
    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public Response create() {
        try {
            creditCardService.create();
            return new Response(HttpStatus.OK, "CreditCard Created Successfully!");
        } catch (RuntimeException exception) {
            return new Response(HttpStatus.Internal_Error, exception.getMessage());
        } catch (Exception exception) {
            return new Response(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }
}
