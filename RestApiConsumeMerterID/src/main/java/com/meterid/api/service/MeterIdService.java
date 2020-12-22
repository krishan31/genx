package com.meterid.api.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meterid.api.dto.MeterIdResponseDto;
import com.meterid.externalapi.MeterIdApiConstantUrl;

@Service

public class MeterIdService {
	MeterIdResponseDto  meterId=new MeterIdResponseDto();

	private final Logger logger = LoggerFactory.getLogger(MeterIdService.class);

		
	public ResponseEntity<MeterIdResponseDto> MeterApiCall(String userId,String discom) {
		
	 logger.info("userid  "+userId +"   discom   "+discom);
		
		 HttpHeaders requestHeaders = new HttpHeaders();
	      requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
	      HttpEntity<String > requestEntity = new HttpEntity<>(requestHeaders);
	      logger.info("response entity "+requestEntity.toString());
	      RestTemplate restTemplate = new RestTemplate();
	      ResponseEntity<String> result=restTemplate.exchange(MeterIdApiConstantUrl.METERIDURLFORNONRAPDRP+"userid="+userId+"&"+"discom="+discom,HttpMethod.GET,requestEntity, String.class);
	      logger.info("response  =======   "+result.getBody());
	   
	       JSONArray object = new JSONArray(result.getBody());  	            
	       	JSONObject object1=object.getJSONObject(0);
	       
	        meterId.setStatus(object1.getString("Status"));
	    	meterId.setLoginId(object1.getString("LoginId"));
	    	meterId.setRemarks(object1.getString("Remarks"));
	    	meterId.setFirstName(object1.getString("FirstName"));
	    	meterId.setLastName(object1.getString("LastName"));
	    	meterId.setDiscom(object1.getString("discom"));	
	    	return new ResponseEntity<MeterIdResponseDto>(meterId, HttpStatus.OK);
	       	
		}
	

}
