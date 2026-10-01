package com.alpha.JobPortal.dto;

import com.alpha.JobPortal.entity.Candidate;

public class ApplicationResponseDto {

	private int id;
	private Candidate candidate; 
	private String status;
	private String applicationdate;
	public ApplicationResponseDto(int id, Candidate candidate, String status, String applicationdate) {
		super();
		this.id = id;
		this.candidate = candidate;
		this.status = status;
		this.applicationdate = applicationdate;
	}
	public ApplicationResponseDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplicationdate() {
		return applicationdate;
	}
	public void setApplicationdate(String applicationdate) {
		this.applicationdate = applicationdate;
	}
	
	
}
