package org.amaap.unusualspends.domain.model.entity.exception.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerEmailValidatorTest {
    CustomerEmailValidator customerEmailValidator = new CustomerEmailValidator();

    @Test
    void shouldBeAbleToValidateCustomerEmailId() {
        assertFalse(customerEmailValidator.isValidEmailId("null"));
        assertFalse(customerEmailValidator.isValidEmailId(""));
        assertFalse(customerEmailValidator.isValidEmailId("@"));
        assertFalse(customerEmailValidator.isValidEmailId("baburao@babu@com"));
        assertFalse(customerEmailValidator.isValidEmailId("baburao,,.apte@example"));
        assertFalse(customerEmailValidator.isValidEmailId("Baburao.apte     @.gmail.com"));
        assertFalse(customerEmailValidator.isValidEmailId("baburao.babu0@com...com."));
        assertFalse(customerEmailValidator.isValidEmailId("456@456"));
        assertFalse(customerEmailValidator.isValidEmailId("baburao@example\t.com"));
        assertFalse(customerEmailValidator.isValidEmailId("baburao@    \t.com"));
        assertFalse(customerEmailValidator.isValidEmailId("\tbaburao@example.com"));
        assertFalse(customerEmailValidator.isValidEmailId("    baburao@example.com"));
        assertFalse(customerEmailValidator.isValidEmailId("baburao@example.com    \t"));
    }

    @Test
    void shouldAbleToReturnTrueWhenCustomerIsValid() {
        assertTrue(customerEmailValidator.isValidEmailId("baburao@example.com"));
        assertTrue(customerEmailValidator.isValidEmailId("baburao_apte123@example.com"));
        assertTrue(customerEmailValidator.isValidEmailId("baburao.apte123@example.co.uk"));
    }
}
