package com.amaap.ttp.creditcard.domain.model;

import java.util.List;
import java.util.Map;

public class UnusualSpend {

    public static double getTotalAmountSpent() {

        List<Transaction> transactions = CreditCard.getTransactions();
        double totalAmountSpent = 0.0;
        for (Transaction transaction : transactions) {
            totalAmountSpent += transaction.getAmount();
        }
        return totalAmountSpent;
    }


    public static List<Transaction> currentMonthTransactions(List<Transaction> transactions) {
        return null;
    }

    public static List<Transaction> previousMonthTransactions(List<Transaction> transactions) {
        return null;
    }

    public static Map<Category, Double> calculateUnusualSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions) {
        return null;
    }
}
