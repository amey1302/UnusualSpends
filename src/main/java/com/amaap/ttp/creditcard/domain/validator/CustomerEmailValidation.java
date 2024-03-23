package com.amaap.ttp.creditcard.domain.validator;

public class CustomerEmailValidation {
    public static boolean isInValidCustomerEmailId(String customerEmailId){
        return !isCustomerEmailIdValid(customerEmailId);
    }
    public static boolean isCustomerEmailIdValid(String customerEmailID) {
        if (customerEmailID == null || customerEmailID.isEmpty() || !customerEmailID.matches("^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$")){
            return false;

        }
        return true;
    }
}
