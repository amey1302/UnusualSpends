package com.amaap.ttp.creditcard.domain.validator;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerValidationTest {
    @Test
    void shouldAbleToValidateCustomerName() {
        CustomerValidation customerValidation = new CustomerValidation();
        assertFalse(customerValidation.isCustomerNameValid(""));
        assertFalse(customerValidation.isCustomerNameValid(null));
        assertFalse(customerValidation.isCustomerNameValid("BA"));
        assertFalse(customerValidation.isCustomerNameValid("   Ba  A"));
        assertFalse(customerValidation.isCustomerNameValid("Ba          "));
        assertFalse(customerValidation.isCustomerNameValid("Baburao"));
        assertFalse(customerValidation.isCustomerNameValid("Baburao A"));
        assertFalse(customerValidation.isCustomerNameValid("@Baburao Ap"));
        assertFalse(customerValidation.isCustomerNameValid("Babur$ Apte"));
        assertFalse(customerValidation.isCustomerNameValid("Baburao Apte%"));
        assertFalse(customerValidation.isCustomerNameValid("$%^* Apte%"));
        assertFalse(customerValidation.isCustomerNameValid("4789Apte%"));
        assertFalse(customerValidation.isCustomerNameValid("9786789089"));


    }

    @Test
    void shouldAbleToValidateCustomerEmailId() {
        CustomerValidation customerValidation = new CustomerValidation();

        // Test cases for invalid email addresses
        assertFalse(customerValidation.isCustomerEmailIdValid("user@example.."));
        assertFalse(customerValidation.isCustomerEmailIdValid("@example.com"));
        assertFalse(customerValidation.isCustomerEmailIdValid("baburao@babu@com"));
        assertFalse(customerValidation.isCustomerEmailIdValid("baburao,,.apte@example"));
        assertFalse(customerValidation.isCustomerEmailIdValid("Baburao.apte     @.gmail.com"));
        assertFalse(customerValidation.isCustomerEmailIdValid("baburao.babu0@com...com."));
        assertFalse(customerValidation.isCustomerEmailIdValid("456@456"));

        // Test cases for valid email addresses
        assertTrue(customerValidation.isCustomerEmailIdValid("baburao@example.com"));
        assertTrue(customerValidation.isCustomerEmailIdValid("baburao_apte123@example.com"));
        assertTrue(customerValidation.isCustomerEmailIdValid("baburao.apte123@example.co.uk"));

    }

}