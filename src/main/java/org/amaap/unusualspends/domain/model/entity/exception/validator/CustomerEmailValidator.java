package org.amaap.unusualspends.domain.model.entity.exception.validator;

public class CustomerEmailValidator {

    public static boolean isValidEmailId(String customerEmailId) {
        return isNotEmpty(customerEmailId) && isEmailFormatValid(customerEmailId);
    }

    public static boolean isInvalidEmailId(String customerEmailId) {
        return !isValidEmailId(customerEmailId);
    }

    private static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private static boolean isEmailFormatValid(String customerEmailId) {
        return customerEmailId.matches("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$");
    }
}

