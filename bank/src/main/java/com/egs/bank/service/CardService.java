package com.egs.bank.service;

import com.egs.bank.entity.Card;
import com.egs.bank.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    protected final CardRepository cardRepository;


    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public Card findByCardNumber(String cardNumber) {
        return cardRepository.findAllByCardNumber(cardNumber);
    }

    public Card findByCardNumberAndPinType(String cardNumber, String pinType) {
        return cardRepository.findAllByCardNumberAndPinType(cardNumber, pinType);
    }
}
