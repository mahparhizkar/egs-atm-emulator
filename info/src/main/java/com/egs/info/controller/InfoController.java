package com.egs.info.controller;

import com.egs.info.dto.ApiResponseDto;
import com.egs.info.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class InfoController {

	@Autowired
	InfoService infoService;

	@RequestMapping(value = "/findServicesUrl", method = RequestMethod.POST)
	public ApiResponseDto findServicesUrl() {
		HashMap<String, String> urls = new HashMap<String, String>();
		urls.put("Admin-Service", "http://localhost:8759/#/wallboard");
		urls.put("Eureka-Service", "http://localhost:8761/");
		urls.put("Swagger", "http://localhost:8760/swagger-ui.html");
		urls.put("Swagger-info-service", "http://localhost:8760/info-service/swagger-ui.html");
		return new ApiResponseDto(true, 0, "Success!", urls);
	}

    @RequestMapping(value = "/getGeneralTokenBeforeLogin", method = RequestMethod.GET)
    public ApiResponseDto getGeneralTokenBeforeLogin() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjYXJkTnVtYmVyIjoiMTA1NTAwMDAwMSJ9.ZUG0mBn7o9ECSUgs-rJrFNxfhMx2J9k6Zomz4EZLeio";
		return new ApiResponseDto(true, 0, "Success!", token);
    }

	@RequestMapping(value = "/getSampleCardNumber", method = RequestMethod.GET)
	public ApiResponseDto getSampleCardNumber() {
		String cardNumber = "1055000001";
		return new ApiResponseDto(true, 0, "Success!", cardNumber);
	}

	@RequestMapping(value = "/getSampleCardNumberAndPin", method = RequestMethod.GET)
	public ApiResponseDto getSampleCardNumberAndPin() {
		String value = "cardNumber: 1055000001, pinType: 1, pinValue: 1234";
		return new ApiResponseDto(true, 0, "Success!", value);
	}

	@RequestMapping(value = "/circuitBreaker", method = RequestMethod.POST)
	public ApiResponseDto circuitBreaker() {
		return infoService.circuitBreaker();
	}
}
