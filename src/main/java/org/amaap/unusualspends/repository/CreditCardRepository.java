package org.amaap.unusualspends.repository;

import org.amaap.unusualspends.domain.model.entity.CreditCard;

public interface CreditCardRepository {
    CreditCard insert(CreditCard creditCard);
}
