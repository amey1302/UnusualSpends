package org.amaap.unusualspends.domain.model.entity;

import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private LocalDate date;
    private double amount;
    private String category;

    private Transaction(int transactionId, LocalDate date, double amount, String category) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public static Transaction create(int transactionId, LocalDate date, double amount, String category) {
        return new Transaction(transactionId, date, amount, category);
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
