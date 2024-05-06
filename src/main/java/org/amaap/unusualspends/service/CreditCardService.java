package org.amaap.unusualspends.service;

import org.amaap.unusualspends.domain.model.entity.CreditCard;
import org.amaap.unusualspends.domain.model.entity.exception.creditcardexception.InvalidCardIdException;
import org.amaap.unusualspends.repository.CreditCardRepository;

public class CreditCardService {
    private CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard create() throws InvalidCardIdException {
        CreditCard creditCard = CreditCard.create(1);
        return creditCardRepository.insert(creditCard);
    }
}
