package com.meterid.api.dto;

//import lombok.Getter;
//import lombok.Setter;


public class MeterIdResponseDto {
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getLoginId() {
		return LoginId;
	}
	public void setLoginId(String loginId) {
		LoginId = loginId;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getDiscom() {
		return discom;
	}
	public void setDiscom(String discom) {
		this.discom = discom;
	}
	public MeterIdResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String Status;
	private String LoginId;
	private String Remarks;
	private String FirstName;
	private String LastName;
	private String discom;
	
}