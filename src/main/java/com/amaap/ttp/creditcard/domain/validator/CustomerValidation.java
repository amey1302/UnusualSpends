package com.amaap.ttp.creditcard.domain.validator;

public class CustomerValidation {
    public static boolean isCustomerNameValid(String name) {
        return name != null && !name.isEmpty() && name.matches("^([A-Za-z]{4,} [A-Za-z]{3,})$");
    }

    public static boolean isCustomerEmailIdValid(String email) {
        if (email == null || email.isEmpty() || !email.matches("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$")){
            return false;

        }
        return true;
    }
}
