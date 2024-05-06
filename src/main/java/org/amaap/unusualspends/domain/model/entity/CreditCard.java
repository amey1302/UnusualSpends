package org.amaap.unusualspends.domain.model.entity;

import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;

public class CreditCard {
    private int id;

    private CreditCard(int id) {
        this.id = id;
    }

    public static CreditCard create(int id) throws InvalidCardIdException {
        return new CreditCard(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                '}';
    }
}
