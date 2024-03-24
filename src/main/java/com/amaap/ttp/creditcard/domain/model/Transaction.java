package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionException;
import com.amaap.ttp.creditcard.domain.model.exception.InvalidTransactionIdException;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private final int transactionId;
    private final LocalDate date;
    private final double amount;

    private final Category category;

    private Transaction(int transactionId, LocalDate date, double amount, Category category) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public static Transaction create(int transactionId, LocalDate date, double amount, Category category) throws InvalidTransactionException {
        if (transactionId <= 0) throw new InvalidTransactionIdException("Invalid Transaction Id " + transactionId);


        return new Transaction(transactionId, date, amount, category);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId && Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, date, amount, category);
    }
}
