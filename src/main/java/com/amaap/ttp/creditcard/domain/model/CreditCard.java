package com.amaap.ttp.creditcard.domain.model;

import com.amaap.ttp.creditcard.domain.model.exception.InvalidCreditCardIdException;

import java.util.Objects;

public class CreditCard {
    private final int creditCardId;

    public CreditCard(int creditCardId) {
        this.creditCardId = creditCardId;
    }
    private static boolean isValidCreditCardId(int creditCardId){
        return creditCardId >= 1;
    }
    public static CreditCard create(int creditCardId) throws InvalidCreditCardIdException {
        if(!isValidCreditCardId(creditCardId)) throw new InvalidCreditCardIdException("Invalid Credit CardID : "+creditCardId);
        return new CreditCard(creditCardId);
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return creditCardId == that.creditCardId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creditCardId);
    }
}
