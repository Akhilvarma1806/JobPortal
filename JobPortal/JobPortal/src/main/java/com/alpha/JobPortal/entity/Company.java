package com.alpha.JobPortal.entity;

import java.util.List;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private long phone;
	private String address;
	private String type;
	private int noOfEmployee;
	@OneToMany(mappedBy = "company",  cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Job> job;
	public Company() {
		super();
	}
	public Company(String name, String email, long phone, String address, String type, int noOfEmployee,
			List<Job> job) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.type = type;
		noOfEmployee = noOfEmployee;
		this.job = job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getnoOfEmployee() {
		return noOfEmployee;
	}
	public void setnoOfEmployee(int noOfEmployee) {
		noOfEmployee = noOfEmployee;
	}
	public List<Job> getJob() {
		return job;
	}
	public void setJob(List<Job> job) {
		this.job = job;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
