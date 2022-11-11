package com.egs.bank.dto;

import com.egs.bank.common.LoginState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponseDto {
    String cardNumber;
    String pinType;
    String pinValue;
    @JsonIgnore
    String random;
    @JsonIgnore
    LoginState state;
    @JsonIgnore
    String token;
    String message;
    int status;
    long lastRequestTimestamp;
}
