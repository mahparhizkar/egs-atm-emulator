package com.egs.atm.dto;

import lombok.Data;

@Data
public class PinValidateRequestDto {
    String cardNumber;
    String pinType;
    String pinValue;
}
