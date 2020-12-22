package com.meterid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meterid.api.dto.MeterIdResponseDto;
import com.meterid.api.service.MeterIdService;

@SpringBootApplication

public class RestApiConsumeMerterIdApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestApiConsumeMerterIdApplication.class, args);
	}
	

}
