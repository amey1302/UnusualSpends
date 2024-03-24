package com.amaap.ttp.creditcard.domain.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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

        List<Transaction> currentMonthTransactions = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Month currentMonth = date.getMonth();

        int currentYear = date.getYear();

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            Month transactionMonth = transactionDate.getMonth();
            int transactionYear = transactionDate.getYear();
            if (transactionMonth == currentMonth && transactionYear == currentYear) {
                currentMonthTransactions.add(transaction);
            }
        }
        return currentMonthTransactions;
    }

    public static List<Transaction> previousMonthTransactions(List<Transaction> transactions) {
        return null;
    }

    public static Map<Category, Double> calculateUnusualSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions) {
        return null;
    }
}
