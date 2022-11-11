package com.egs.atm.service;

import com.egs.atm.dto.ApiResponseDto;
import com.egs.atm.dto.PinValidateRequestDto;
import com.egs.atm.security.send.JwtRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtmService {

    @Value("${url.bank-service.verifyCardNumber}")
    String bankUrlCardVerify;

    @Value("${url.bank-service.verifyPin}")
    String bankUrlPinVerify;

    @Value("${url.bank-service.operationList}")
    String bankUrlOperationList;

    @Autowired
    JwtRestTemplate jwtRestTemplate;

    public ApiResponseDto cardValidate(String cardNumber) {
        try {
            if (checkCardNumberAlgorithm(cardNumber)) {
                ApiResponseDto response = validateCardNumber(cardNumber);

                if (response.isSuccess()) {
                    return new ApiResponseDto(true, 0, "Success!", response.getResultData());
                } else {
                    return new ApiResponseDto(false, -1, "Failed! " + response.getMessage());
                }
            } else {
                return new ApiResponseDto(false, -2, "Failed! CardNumber is not valid!");
            }
        } catch (Exception e) {
            return new ApiResponseDto(false, -3, "Failed! Some exception occurred. " + e.getMessage());
        }
    }

    public ApiResponseDto pinValidate(PinValidateRequestDto request) {
        try {
            if (checkPinRequest(request)) {
                ApiResponseDto response = validatePin(request);

                if (response.isSuccess()) {
                    return new ApiResponseDto(true, 0, "Success!", response.getResultData());
                } else {
                    return new ApiResponseDto(false, -1, "Failed! " + response.getMessage());
                }
            } else {
                return new ApiResponseDto(false, -2, "Failed! CardNumber, pinType or pinValue are not valid!");
            }
        } catch (Exception e) {
            return new ApiResponseDto(false, -3, "Failed! Some exception occurred. " + e.getMessage());
        }
    }

    public ApiResponseDto getOperationList() {
        try {
            ApiResponseDto response = getOperations();

            if (response.isSuccess()) {
                return new ApiResponseDto(true, 0, "Success!", response.getResultData());
            } else {
                return new ApiResponseDto(false, -1, "Failed! " + response.getMessage());
            }
        } catch (Exception e) {
            return new ApiResponseDto(false, -2, "Failed! Some exception occurred. " + e.getMessage());
        }
    }

    private boolean checkCardNumberAlgorithm(String cardNumber) throws Exception {
        try {
            if (cardNumber.isEmpty()) {
                return false;
            }
            if (cardNumber.length() != 10) {
                return false;
            }
            if (!cardNumber.matches("[0-9]+")) {
                return false;
            }
            if (!cardNumber.substring(0, 2).equals("10")) {
                return false;
            }

            int num = Integer.valueOf(cardNumber.substring(2,7));
            int r, sum=0;

            while(num>0) {
                r = num%10;
                sum = sum + r;
                num = num/10;

            }
            if (sum != 10) {
                return false;
            }
            return true;

        } catch (Exception e) {
            throw new Exception("Error happened: ", e);
        }
    }

    private ApiResponseDto validateCardNumber (String cardNumber) {
        return jwtRestTemplate.jwtPost(bankUrlCardVerify, cardNumber, ApiResponseDto.class);
    }

    private boolean checkPinRequest(PinValidateRequestDto request) throws Exception {
        try {
            if (!checkCardNumberAlgorithm(request.getCardNumber())) {
                return false;
            }
            if (request.getPinType().isEmpty() || request.getPinValue().isEmpty()) {
                return false;
            }
            if (request.getPinType().equals("1") && request.getPinValue().length() != 4) {
                return false;
            }
            if (request.getPinType().equals("2") && request.getPinValue().length() > 2048) {
                return false;
            }
            if (!request.getPinType().matches("[0-9]+") || !request.getPinValue().matches("[0-9]+")) {
                return false;
            }

            return true;

        } catch (Exception e) {
            throw new Exception("Error happened: ", e);
        }
    }

    private ApiResponseDto validatePin (PinValidateRequestDto request) {
        return jwtRestTemplate.jwtPost(bankUrlPinVerify, request, ApiResponseDto.class);
    }

    private ApiResponseDto getOperations () {
        return jwtRestTemplate.jwtPost(bankUrlOperationList, null, ApiResponseDto.class);
    }
}
