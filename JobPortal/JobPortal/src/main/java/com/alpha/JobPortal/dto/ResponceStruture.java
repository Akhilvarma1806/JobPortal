package com.alpha.JobPortal.dto;

import org.springframework.http.HttpStatus;

public class ResponceStruture<T> {
	private int statuCode;
	private String message;
	private T data;
	public ResponceStruture() {
		super();
	}
	public ResponceStruture(int statuCode, String message, T data) {
		super();
		this.statuCode = statuCode;
		this.message = message;
		this.data = data;
	}
	public int getStatuCode() {
		return statuCode;
	}
	public void setStatuCode(int statuCode) {
		this.statuCode = statuCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
