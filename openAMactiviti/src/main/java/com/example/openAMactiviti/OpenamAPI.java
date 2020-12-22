package com.example.openAMactiviti;

public class OpenamAPI {
	public static final String TOKEN = "http://openam.example.com:8080/openam/json/authenticate";
//	public static final String USERDATA = "http://openam.example.com:8080/openam/json/users/amAdmin/";
	public static final String USERDATA = "http://openam.example.com:8080/openam/json/users?_queryFilter=true&_fields=username,cn,sn&_pageSize=3";
}
