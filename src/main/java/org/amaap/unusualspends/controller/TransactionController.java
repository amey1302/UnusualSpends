package org.amaap.unusualspends.controller;

import org.amaap.unusualspends.controller.dto.HttpStatus;
import org.amaap.unusualspends.controller.dto.Response;
import org.amaap.unusualspends.service.TransactionService;
import org.amaap.unusualspends.service.exception.transactionexception.InvalidTransactionException;

import java.time.LocalDate;

public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response create(int transactionId, LocalDate date, double amount, String category) throws InvalidTransactionException {
        try {
            transactionService.create(transactionId, date, amount, category);
            return new Response(HttpStatus.OK, "Transaction Performed");
        } catch (InvalidTransactionException exception) {
            return new Response(HttpStatus.BAD_REQUEST, "Invalid Transaction Details");
        }
    }
}
