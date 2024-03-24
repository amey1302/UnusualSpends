package com.amaap.ttp.creditcard.domain.DTO;

import com.amaap.ttp.creditcard.domain.model.Category;
import com.amaap.ttp.creditcard.domain.model.Customer;

import java.util.Map;

public class UnusualSpendingEmailAlertDTO {
    private Customer customer;
    private Map<Category, Double> unusualSpends;

    private double totalUnusualAmount;

    public UnusualSpendingEmailAlertDTO(Customer customer, Map<Category, Double> unusualSpends, double totalUnusualAmount) {
        this.customer = customer;
        this.unusualSpends = unusualSpends;
        this.totalUnusualAmount = totalUnusualAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Map<Category, Double> getUnusualSpends() {
        return unusualSpends;
    }

    public void setUnusualSpends(Map<Category, Double> unusualSpends) {
        this.unusualSpends = unusualSpends;
    }

    public double getTotalUnusualAmount() {
        return totalUnusualAmount;
    }

    public void setTotalUnusualAmount(double totalUnusualAmount) {
        this.totalUnusualAmount = totalUnusualAmount;
    }
}
