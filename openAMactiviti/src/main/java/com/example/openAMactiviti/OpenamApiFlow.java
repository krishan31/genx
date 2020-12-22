package com.example.openAMactiviti;

import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OpenamApiFlow {
	
	public String username, cn, sn;

	
	
	public static void  Openauthentication() {
		  HttpHeaders requestHeaders = new HttpHeaders();
		  String Username="amAdmin";
		  String Password ="qwerty12345";
		  requestHeaders.add("X-OpenAM-Username",Username);
		  requestHeaders.add("X-OpenAM-Password",Password);
		  requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        	HttpEntity<String > requestEntity = new HttpEntity<>(requestHeaders);
        	System.out.println("requestEntity ==============================  "+requestEntity);
       // 	logger.info("response entity "+requestEntity.toString());
        	RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<String> result=restTemplate.exchange(OpenamAPI.TOKEN, HttpMethod.POST,requestEntity, String.class);
        	JSONObject object = new JSONObject(result.getBody());
        	System.out.println("json object response" + object);
        	String tokenId = (String) object.get("tokenId");
        	//TokenValidation(tokenId);
        	Admindetail(tokenId);
	}
	
	public static void Admindetail(String tokenId) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("iPlanetDirectoryPro",tokenId);
		requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
		System.out.println("requestEntity============="+requestEntity);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result=restTemplate.exchange(OpenamAPI.USERDATA,HttpMethod.GET,requestEntity, String.class);
		JSONObject objectd = new JSONObject(result.getBody());
		System.out.println("User Details :"+objectd);
//		System.out.println("User Details :"+objectd.get("username"));
		String username = (String) objectd.get("username");
		String sn = (String) objectd.get("sn");
		String cn = (String) objectd.get("cn");
	}
}
