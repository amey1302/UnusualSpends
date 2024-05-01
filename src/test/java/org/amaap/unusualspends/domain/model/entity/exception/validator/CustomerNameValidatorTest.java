package org.amaap.unusualspends.domain.model.entity.exception.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNameValidatorTest {
    @Test
    void shouldAbleToValidateCustomerName() {
        CustomerNameValidator customerValidation = new CustomerNameValidator();
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

}