package com.egs.bank.controller;

import com.egs.bank.dto.ApiResponseDto;
import com.egs.bank.dto.PinValidateRequestDto;
import com.egs.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class BankController {

	@Autowired
	BankService bankService;

	@RequestMapping(value = "/verifyCardNumber", method = RequestMethod.POST)
	public ApiResponseDto verifyCardNumber(@RequestBody String cardNumber) {
		return bankService.cardValidate(cardNumber);
	}

	@RequestMapping(value = "/verifyPin", method = RequestMethod.POST)
	public ApiResponseDto verifyPin(@RequestBody PinValidateRequestDto request) {
		return bankService.pinValidate(request);
	}

	@RequestMapping(value = "/operationList", method = RequestMethod.POST)
	public ApiResponseDto getOperationList() {
		HashMap<String, String> operations = new HashMap<String, String>();
		operations.put("1", "Cash deposit");
		operations.put("2", "Cash withdrawal");
		operations.put("3", "Check balance");
		return new ApiResponseDto(true, 0, "Success!", operations);
	}
}
