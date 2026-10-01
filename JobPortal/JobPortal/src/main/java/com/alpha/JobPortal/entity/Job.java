package com.alpha.JobPortal.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String role;
	@OneToMany(mappedBy= "job",cascade = CascadeType.ALL)
	private List<Skill> reqSkills;
	private String jobDesc;
	private int noOfPosition;
	private int salary;
	private int bond;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="company_id")
	private Company company;
	private int 	reqExperince;
	private String postDate;
	private String lastDate;
	private String reqQualifcation;
	@OneToMany(mappedBy= "job",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Application> application;
	private String status;
	public Job() {
		super();
	}
	
	public Job(int id, String role, List<Skill> reqSkills, String jobDesc, int noOfPosition, int salary, int bond,
			Company company, int reqExperince, String postDate, String lastDate, String reqQualifcation,
			List<Application> application, String status) {
		super();
		this.id = id;
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
		this.application = application;
		this.status = status;
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
	public List<Application> getApplication() {
		return application;
	}
	public void setApplication(List<Application> application) {
		this.application = application;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
