package com.amaap.ttp.creditcard.domain.services;

import com.amaap.ttp.creditcard.domain.DTO.UnusualSpendingEmailAlertDTO;
import com.amaap.ttp.creditcard.domain.model.Category;
import com.amaap.ttp.creditcard.domain.model.Customer;

import java.util.Map;

public class EmailAlert {
    public static String generateUnusualSpendingEmail(UnusualSpendingEmailAlertDTO emailDTO) {
        Customer customer = emailDTO.getCustomer();
        Map<Category, Double> unusualSpends = emailDTO.getUnusualSpends();
        double totalUnusualAmount = emailDTO.getTotalUnusualAmount();

        StringBuilder emailContent = new StringBuilder();
        emailContent.append("Unusual spending of RS.").append(totalUnusualAmount).append(" detected!\n\n");
        emailContent.append("Hello ").append(customer.getName()).append("!\n\n");
        emailContent.append("We have detected unusually high spending on your card in these categories:\n\n");

        for (Map.Entry<Category, Double> entry : unusualSpends.entrySet()) {
            emailContent.append("* You spent RS.").append(entry.getValue()).append(" on ").append(entry.getKey()).append("\n");
        }

        emailContent.append("\nThanks,\n");
        emailContent.append("The Credit Card Company");

        //System.out.println(emailContent.toString());

        return emailContent.toString();

    }

}
