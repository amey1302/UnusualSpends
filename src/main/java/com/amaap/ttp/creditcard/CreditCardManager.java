package com.amaap.ttp.creditcard;

import com.amaap.ttp.creditcard.domain.model.*;
import com.amaap.ttp.creditcard.domain.exception.customerexception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.exception.creditcardexception.InvalidCreditCardIdException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class CreditCardManager {
    public Customer createCustomer(int customerId, String customerName, String customerEmail) throws CustomerValidationException {
        // Create a customer using the provided data
        Customer customer = Customer.create(customerId, customerName, customerEmail);
        return customer;
    }

    public void createCreditCardForCustomer(int creditCardId, Customer customer) throws InvalidCreditCardIdException {
        CreditCard creditCard = CreditCard.create(creditCardId);
        customer.setCreditCard(creditCard);
    }

    public void createTransactionForACreditCard(int transactionId, LocalDate date, double amount, Category category, CreditCard creditCard) throws InvalidTransactionException {
        Transaction transaction = Transaction.create(transactionId,date,amount,category);
        creditCard.addTransaction(transaction);
    }


    public boolean analyzeUnusualSpendsFor(CreditCard creditCard) {

        List<Transaction> transactions = creditCard.getTransactions();
        List<Transaction> currentMonthTransactions = UnusualSpend.currentMonthTransactions(transactions);
        List<Transaction> previousMonthTransactions = UnusualSpend.previousMonthTransactions(transactions);
        Map<Category, Double> unusualSpendTransactions = UnusualSpend.calculateUnusualSpend(currentMonthTransactions,previousMonthTransactions,50.0);

        if(unusualSpendTransactions.size()!=0)
            return true;
        return false;


    }
}
