package com.example.openAMactiviti;

import java.util.List;

//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */

public class Result{
public String username;
public List<String> cn;
public List<String> sn;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public List<String> getCn() {
	return cn;
}
public void setCn(List<String> cn) {
	this.cn = cn;
}
public List<String> getSn() {
	return sn;
}
public void setSn(List<String> sn) {
	this.sn = sn;
}
}

