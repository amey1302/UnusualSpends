package com.amaap.ttp.creditcard.domain.services;

import com.amaap.ttp.creditcard.domain.DTO.UnusualSpendingEmailAlertDTO;
import com.amaap.ttp.creditcard.domain.exception.customerexception.CustomerValidationException;
import com.amaap.ttp.creditcard.domain.model.Category;
import com.amaap.ttp.creditcard.domain.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailAlertTest {


    @Test
    void testGenerateUnusualSpendingEmail() throws CustomerValidationException {
        // Create a sample customer
        Customer customer = Customer.createCustomer(1, "Baburao Apte", "baburao@example.com");

        // Create a sample map of unusual spends
        Map<Category, Double> unusualSpends = new HashMap<>();
        unusualSpends.put(Category.Travel, 928.0);
        unusualSpends.put(Category.Groceries, 148.0);


        // Create the UnusualSpendingEmailNotificationDTO object
        UnusualSpendingEmailAlertDTO emailDTO = new UnusualSpendingEmailAlertDTO(customer,unusualSpends,1076.0);
        emailDTO.setCustomer(customer);
        emailDTO.setUnusualSpends(unusualSpends);
        emailDTO.setTotalUnusualAmount(1076.0);

        // Generate the email
        String expectedEmailContent = "Unusual spending of RS.1076.0 detected!\n\n" +
                "Hello Baburao Apte!\n\n" +
                "We have detected unusually high spending on your card in these categories:\n\n" +
                "* You spent RS.148.0 on Groceries\n" +
                "* You spent RS.928.0 on Travel\n\n" +
                "Thanks,\n" +
                "The Credit Card Company";

        String actualEmailContent = EmailAlert.generateUnusualSpendingEmail(emailDTO);

        // Assert
        assertEquals(expectedEmailContent, actualEmailContent);
    }
}

