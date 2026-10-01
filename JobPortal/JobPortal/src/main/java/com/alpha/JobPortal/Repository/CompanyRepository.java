package com.alpha.JobPortal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.JobPortal.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
	public Optional<Company> findByPhone(long phone);
	public Optional<Company> findByEmail(String mail);
}
