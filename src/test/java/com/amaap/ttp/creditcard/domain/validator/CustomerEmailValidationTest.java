package com.amaap.ttp.creditcard.domain.validator;

import com.amaap.ttp.creditcard.validator.CustomerEmailValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerEmailValidationTest {

    @Test
    void shouldAbleToValidateCustomerEmailId() {
        CustomerEmailValidation customerEmailValidation = new CustomerEmailValidation();

        // Test cases for invalid email addresses
        assertFalse(customerEmailValidation.isCustomerEmailIdValid("user@example.."));
        assertFalse(customerEmailValidation.isCustomerEmailIdValid("@example.com"));
        assertFalse(customerEmailValidation.isCustomerEmailIdValid("baburao@babu@com"));
        assertFalse(customerEmailValidation.isCustomerEmailIdValid("baburao,,.apte@example"));
        assertFalse(customerEmailValidation.isCustomerEmailIdValid("Baburao.apte     @.gmail.com"));
        assertFalse(customerEmailValidation.isCustomerEmailIdValid("baburao.babu0@com...com."));
        assertFalse(customerEmailValidation.isCustomerEmailIdValid("456@456"));

        // Test cases for valid email addresses
        assertTrue(customerEmailValidation.isCustomerEmailIdValid("baburao@example.com"));
        assertTrue(customerEmailValidation.isCustomerEmailIdValid("baburao_apte123@example.com"));
        assertTrue(customerEmailValidation.isCustomerEmailIdValid("baburao.apte123@example.co.uk"));

    }


}