package com.meterid.api.dto;

public class RequestParameter {
	private String UserId;
	private String Discom;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getDiscom() {
		return Discom;
	}
	public void setDiscom(String discom) {
		Discom = discom;
	}
	public RequestParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

}
