package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.Transaction;
import org.amaap.unusualspends.repository.TransactionRepository;
import org.amaap.unusualspends.service.exception.transactionexception.*;

import java.time.LocalDate;

import static org.amaap.unusualspends.service.validator.transactionvalidator.TransactionValidator.*;

public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction create(int transactionId, LocalDate date, double amount, String category) throws InvalidTransactionException {
        if (transactionId <= 0) throw new InvalidTransactionIdException("Invalid Transaction Id " + transactionId);
        if (isInvalidTransactionAmount(amount))
            throw new InvalidTransactionAmountException("Transaction Amount Should be Greater Than 0 " + amount);
        if (isInvalidTransactionDate(date))
            throw new InvalidTransactionDateException("Transaction Date Should be In Format(yyyy,MM,dd)");
        if (isInvalidTransactionCategory(category))
            throw new InvalidTransactionCategoryException("Invalid Transaction Category " + category);
        Transaction transaction = Transaction.create(transactionId, date, amount, category);
        return transactionRepository.insert(transaction);
    }

}
