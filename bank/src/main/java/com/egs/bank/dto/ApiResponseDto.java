package com.egs.bank.dto;

import lombok.Data;

@Data
public class ApiResponseDto<T> {
    boolean isSuccess;
    int rsCode;
    String message;
    T resultData;

    public ApiResponseDto() {
    }

    public ApiResponseDto(boolean isSuccess, int rsCode, String message) {
        this.isSuccess = isSuccess;
        this.rsCode = rsCode;
        this.message = message;
    }

    public ApiResponseDto(boolean isSuccess, int rsCode, String message, T resultData) {
        this.isSuccess = isSuccess;
        this.rsCode = rsCode;
        this.message = message;
        this.resultData = resultData;
    }
}

