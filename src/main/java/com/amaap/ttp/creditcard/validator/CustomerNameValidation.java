package com.amaap.ttp.creditcard.validator;

public class CustomerNameValidation {
    public static boolean isInValidCustomerName(String customerName){
        return !isCustomerNameValid(customerName);
    }
    public static boolean isCustomerNameValid(String name) {
        return name != null && !name.isEmpty() && name.matches("^([A-Za-z]{4,} [A-Za-z]{3,})$");
    }


}
