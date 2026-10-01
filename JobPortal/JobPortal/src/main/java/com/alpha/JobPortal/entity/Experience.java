package com.alpha.JobPortal.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Experience {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int noOfYear;
	private String role;
	private String comapanyName;
	private String startDate;
	private String endDate;
	private String working;
	private String description;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Skill> skills;
	public Experience(int noOfYear, String role, String comapanyName, String startDate, String endDate, String working,
			String description, List<Skill> skills) {
		super();
		this.noOfYear = noOfYear;
		this.role = role;
		this.comapanyName = comapanyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.working = working;
		this.description = description;
		this.skills = skills;
	}
	public Experience() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNoOfYear() {
		return noOfYear;
	}
	public void setNoOfYear(int noOfYear) {
		this.noOfYear = noOfYear;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getComapanyName() {
		return comapanyName;
	}
	public void setComapanyName(String comapanyName) {
		this.comapanyName = comapanyName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWorking() {
		return working;
	}
	public void setWorking(String working) {
		this.working = working;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	
	
	
	
}
