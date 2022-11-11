package com.egs.bank.service;

import com.egs.bank.dto.ApiResponseDto;
import com.egs.bank.dto.PinValidateRequestDto;
import com.egs.bank.entity.Card;
import com.egs.bank.security.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    SessionHandler sessionHandler;

    @Autowired
    CardService cardService;

    public ApiResponseDto cardValidate(String cardNumber) {
        try {
            Card card = cardService.findByCardNumber(cardNumber);
            if (card != null) {
                return new ApiResponseDto(true, 0, "Success!", card.getPinType());
            } else {
                return new ApiResponseDto(false, -1, "CardNumber is not exist!");
            }
        } catch (Exception e) {
            return new ApiResponseDto(false, -2, "Some exception occurred. " + e.getMessage());
        }
    }

    public ApiResponseDto pinValidate(PinValidateRequestDto request) {
        try {
            Card card = cardService.findByCardNumberAndPinType(request.getCardNumber(), request.getPinType());
            if (card != null) {
                if (request.getPinValue().equals(card.getPinValue())) {
                    if (card.getRemainCountBlock() > 0) {
                        card.setRemainCountBlock(3);
                        cardService.save(card);
                        String token = sessionHandler.addSession(request.getCardNumber(), request.getPinType(), request.getPinValue()).getToken();
                        return new ApiResponseDto(true, 0, "Success!", token);
                    } else {
                        return new ApiResponseDto(false, -1, "CardNumber is blocked!");
                    }
                } else {
                    card.setRemainCountBlock(Math.max(0, card.getRemainCountBlock()-1));
                    cardService.save(card);
                    return new ApiResponseDto(false, -2, "PinValue is not correct!");
                }
            } else {
                return new ApiResponseDto(false, -3, "CardNumber or pinType are not correct!");
            }
        } catch (Exception e) {
            return new ApiResponseDto(false, -4, "Some exception occurred. " + e.getMessage());
        }
    }
}
