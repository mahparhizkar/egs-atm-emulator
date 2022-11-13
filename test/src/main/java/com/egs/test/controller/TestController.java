package com.egs.test.controller;

import com.egs.test.dto.ApiResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class TestController {

    @RequestMapping(value = "/operationList", method = RequestMethod.POST)
    public ApiResponseDto getOperationList() {
		HashMap<String, String> operations = new HashMap<String, String>();
		operations.put("1", "Cash deposit");
		operations.put("2", "Cash withdrawal");
		operations.put("3", "Check balance");
		return new ApiResponseDto(true, 0, "Success!", operations);
    }
}
