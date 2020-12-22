package com.meterid.api.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.meterid.api.dto.MeterIdResponseDto;
import com.meterid.api.dto.RequestParameter;
import com.meterid.api.service.MeterIdService;


@RestController
@RequestMapping(value="/api")
public class MeterIdRestController {
	private MeterIdService meterIdService;
	

	public MeterIdRestController(MeterIdService meterIdService) {
		super();
		this.meterIdService = meterIdService;
	}

	
	@PostMapping("/details")
	public ResponseEntity<MeterIdResponseDto> Callapi(@RequestBody RequestParameter parameter)
	{
			return meterIdService.MeterApiCall(parameter.getUserId(),parameter.getDiscom());

	}
	
}
