package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionAmountException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionDateException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionException;
import com.amaap.ttp.creditcard.domain.exception.transactionexception.InvalidTransactionIdException;

import java.time.DateTimeException;
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
        if (isInvalidTransactionAmount(amount)) throw new InvalidTransactionAmountException("Transaction Amount Should be Greater Than 100 " + amount);
        if(isInvalidTransactionDate(date)) throw new InvalidTransactionDateException("Transaction Date Should be In Format(yyyy,MM,dd)");

        return new Transaction(transactionId, date, amount, category);
    }

    private static boolean isInvalidTransactionDate(LocalDate date) throws InvalidTransactionDateException {
        try {
            // Attempt to create a date using the provided LocalDate
            LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
            return false; // Date is valid
        } catch (DateTimeException e) {
            // Date is invalid, throw InvalidTransactionDateException
            throw new InvalidTransactionDateException("Invalid transaction date: " + date +" "+e);
        }
    }

    private static boolean isInvalidTransactionAmount(double amount) {
        return !isValidTransactionAmount(amount);
    }

    private static boolean isValidTransactionAmount(double amount) {
        return amount > 0;
    }


    public int getTransactionId() {
        return transactionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
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
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }
}
