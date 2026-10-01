package com.alpha.JobPortal.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.JobPortal.entity.Application;
@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer>{

	boolean existsByJobIdAndCandidateId(int jobid,int candid);
	Optional<Application> findByIdAndCandidateId(int id,int canid); 
	Optional<List<Application>> findByCandidateId(int candid);
	List<Application> findByCandidateIdAndStatusIgnoreCase(int candid,String status);
}
