package com.alpha.JobPortal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public class CandidateRegisterDto {
	@NotBlank(message = "Name should not be blank")
	private String name;
	@Email(message = "enter a valid mail")
	private String mail;
	@Min(value = 10,message = "Phone Must be of 10 digits")
	@Max(value = 10,message = "Phone Must be of 10 digits")
	private long phone;
	@Min(value = 1,message = "age must be between 1 and 100")
	@Max(value = 100,message = "age must be between 1 and 100")
	private int age;
	
	private String gender;
	public CandidateRegisterDto() {
		super();
	}
	public CandidateRegisterDto(String name, String mail, long phone, int age, String gender) {
		super();
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
