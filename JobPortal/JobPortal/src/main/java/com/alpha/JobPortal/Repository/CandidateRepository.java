package com.alpha.JobPortal.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.JobPortal.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	 public Optional<Candidate> findByPhone(long phone);
	 public Optional<Candidate> findByEmail(String mail);
}
