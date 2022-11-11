package com.egs.bank.dto;

import lombok.Data;

@Data
public class PinValidateRequestDto {
    String cardNumber;
    String pinType;
    String pinValue;
}
