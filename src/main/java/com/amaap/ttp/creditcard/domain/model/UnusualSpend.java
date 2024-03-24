package com.amaap.ttp.creditcard.domain.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<Transaction> previousMonthTransactions = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();
        int currentYear = currentDate.getYear();

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            Month transactionMonth = transactionDate.getMonth();
            int transactionYear = transactionDate.getYear();

            Month previousMonth = currentMonth.minus(1);
            int previousYear = currentYear;

            if (previousMonth == Month.DECEMBER) {
                previousYear--;
            }

            if (transactionMonth == previousMonth && transactionYear == previousYear) {
                previousMonthTransactions.add(transaction);
            }
        }
        return previousMonthTransactions;
    }

    public static Map<Category, Double> calculateUnusualSpend(List<Transaction> currentMonthTransactions, List<Transaction> previousMonthTransactions) {
        Map<Category, Double> unusualSpends = new HashMap<>();
        Map<Category, Double> currentMonthSpending = totalSpending(currentMonthTransactions);
        Map<Category, Double> previousMonthSpending = totalSpending(previousMonthTransactions);

        for (Category category : Category.values()) {
            double currentMonthAmount = currentMonthSpending.getOrDefault(category, 0.0);
            double previousMonthAmount = previousMonthSpending.getOrDefault(category, 0.0);

            double percentageChange = ((currentMonthAmount - previousMonthAmount) / previousMonthAmount) * 100;
            if (percentageChange >= 50.0)
                unusualSpends.put(category, currentMonthAmount - previousMonthAmount);

        }
        return unusualSpends;
    }

    public static double totalUnusualAmountSpend(Map<Category, Double> unusualSpends) {
        double totalUnusualAmount = 0.0;
        for (double amount : unusualSpends.values()) {
            totalUnusualAmount += amount;
        }
        return totalUnusualAmount;
    }

    public static Map<Category, Double> totalSpending(List<Transaction> transactions) {
        Map<Category, Double> totalAmount = new HashMap<>();
        for (Transaction transaction : transactions) {
            Category category = transaction.getCategory();
            Double amount = transaction.getAmount();
            totalAmount.put(category, totalAmount.getOrDefault(category, 0.0) + amount);

        }
        return totalAmount;
    }
}
