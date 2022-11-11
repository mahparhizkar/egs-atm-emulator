package com.egs.atm.controller;

import com.egs.atm.dto.ApiResponseDto;
import com.egs.atm.dto.CardValidateRequestDto;
import com.egs.atm.dto.PinValidateRequestDto;
import com.egs.atm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AtmController {

	@Autowired
	AtmService atmService;

	@RequestMapping(value = "/verifyCardNumber", method = RequestMethod.POST)
	public ApiResponseDto verifyCardNumber(@RequestBody CardValidateRequestDto request) {
		return atmService.cardValidate(request.getCardNumber());
	}

	@RequestMapping(value = "/verifyPin", method = RequestMethod.POST)
	public ApiResponseDto verifyPin(@RequestBody PinValidateRequestDto request) {
		return atmService.pinValidate(request);
	}

    @RequestMapping(value = "/operationList", method = RequestMethod.POST)
    public ApiResponseDto getOperationList() {
        return atmService.getOperationList();
    }
}
