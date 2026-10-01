package com.alpha.JobPortal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String qualification;
	private String specialization;
	private double percentage;
	private String yop;
	private String university;
	public Education(String qualification, String specialization, double percentage, String yop, String university) {
		super();
		this.qualification = qualification;
		this.specialization = specialization;
		this.percentage = percentage;
		this.yop = yop;
		this.university = university;
	}
	public Education() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getYop() {
		return yop;
	}
	public void setYop(String yop) {
		this.yop = yop;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	
	
	
	
	
}
