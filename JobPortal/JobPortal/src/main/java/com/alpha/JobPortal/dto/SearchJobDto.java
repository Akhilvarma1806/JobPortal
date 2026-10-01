package com.alpha.JobPortal.dto;

import java.util.List;

import com.alpha.JobPortal.entity.Company;
import com.alpha.JobPortal.entity.Skill;



public class SearchJobDto {

	private int id;
	private String role;
	private List<Skill> reqSkills;
	private String jobDesc;
	private int noOfPosition;
	private int salary;
	private int bond;
	private Company company;
	private int reqExperince;
	private String postDate;
	private String lastDate;
	private String reqQualifcation;
	private String status;
	public SearchJobDto(String role, List<Skill> reqSkills, String jobDesc, int noOfPosition, int salary, int bond,
			Company company, int reqExperince, String postDate, String lastDate, String reqQualifcation,
			String status) {
		super();
		this.role = role;
		this.reqSkills = reqSkills;
		this.jobDesc = jobDesc;
		this.noOfPosition = noOfPosition;
		this.salary = salary;
		this.bond = bond;
		this.company = company;
		this.reqExperince = reqExperince;
		this.postDate = postDate;
		this.lastDate = lastDate;
		this.reqQualifcation = reqQualifcation;
		this.status = status;
	}
	public SearchJobDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Skill> getReqSkills() {
		return reqSkills;
	}
	public void setReqSkills(List<Skill> reqSkills) {
		this.reqSkills = reqSkills;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public int getNoOfPosition() {
		return noOfPosition;
	}
	public void setNoOfPosition(int noOfPosition) {
		this.noOfPosition = noOfPosition;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getBond() {
		return bond;
	}
	public void setBond(int bond) {
		this.bond = bond;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getReqExperince() {
		return reqExperince;
	}
	public void setReqExperince(int reqExperince) {
		this.reqExperince = reqExperince;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getReqQualifcation() {
		return reqQualifcation;
	}
	public void setReqQualifcation(String reqQualifcation) {
		this.reqQualifcation = reqQualifcation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
