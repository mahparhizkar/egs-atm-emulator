package com.egs.info.service;

import com.egs.info.dto.ApiResponseDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfoService {

    @Value("${url.atm-service.verifyCardNumber}")
    String url;

    @HystrixCommand(fallbackMethod="fallBack")
    public ApiResponseDto circuitBreaker() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(url + null, String.class);
        return new ApiResponseDto(true, 0, "Success!");
    }

    public ApiResponseDto fallBack() {
        return new ApiResponseDto(false, -1, "FallBack CircuitBreaker Called.");
    }
}
