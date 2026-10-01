package com.alpha.JobPortal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.JobPortal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{

	List<Job> findByRoleContainingIgnoreCaseOrReqSkills_SkillContainingIgnoreCase(String role,String skill);
	List<Job> findByCompany_Id(int compid);
	Job findByIdAndCompany_Id(int jobid,int companyid);
	List<Job> findByCompany_IdAndStatusIgnoreCase(int comid,String status);
}
