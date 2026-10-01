package com.alpha.JobPortal.dto;

public class CompanyRegisterDto {
	private String name;
	private String mail;
	private long phone;
	private String address;
	private String compType;
	private int noEmp;
	public CompanyRegisterDto() {
		super();
	}
	public CompanyRegisterDto(String name, String mail, long phone, String address, String compType, int noEmp) {
		super();
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.address = address;
		this.compType = compType;
		this.noEmp = noEmp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompType() {
		return compType;
	}
	public void setCompType(String compType) {
		this.compType = compType;
	}
	public int getNoEmp() {
		return noEmp;
	}
	public void setNoEmp(int noEmp) {
		this.noEmp = noEmp;
	}
	
}
