package com.alpha.JobPortal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.JobPortal.dto.ApplicationResponseDto;
import com.alpha.JobPortal.dto.CandidateRegisterDto;
import com.alpha.JobPortal.dto.ResponceStruture;
import com.alpha.JobPortal.dto.SearchJobDto;
import com.alpha.JobPortal.entity.Application;
import com.alpha.JobPortal.entity.Candidate;
import com.alpha.JobPortal.entity.Education;
import com.alpha.JobPortal.entity.Experience;
import com.alpha.JobPortal.entity.Skill;
import com.alpha.JobPortal.service.CandiateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobportal")
public class CandidateController {
	@Autowired
	private CandiateService candidateServie;
	
	@PostMapping("/candidate/register")
	public ResponceStruture<Candidate> candidateRegister(@Valid @RequestBody CandidateRegisterDto candDto) {
		return candidateServie.candidateRegister(candDto);
	}
	
	@DeleteMapping("/candidate/delete/{id}")
	public ResponceStruture<String> candidateDelete(@PathVariable int id) {
		return candidateServie.candidateDelete(id); 
	}
	
	@GetMapping("/candidate/find/{id}") 
	public ResponceStruture<Candidate> candidateFind(@PathVariable int id) {
		return candidateServie.candidateFind(id);
	}
	
	@GetMapping("/candidate/job/find")
	public ResponceStruture<List<SearchJobDto>> findBySkey(@RequestParam String skey) {
		return candidateServie.findBySkey(skey);
	}
	
	@PostMapping("/candidate/job/apply")
	public ResponceStruture<Application> applyJob(@RequestParam int jobid,int candid) {
		return candidateServie.applicationForJob(jobid,candid);
	}
	
	@GetMapping("/candidate/checkstatus")
	public ResponceStruture<String> checkApplicationStatus(@RequestParam int candid,int appid) {
		return candidateServie.checkApplicationStatus(candid,appid);
	}
	
	@GetMapping("/candidate/getallapplications")
	public ResponceStruture<List<ApplicationResponseDto>> getAllApplications(@RequestParam int candid) {
		return candidateServie.getAllApplications(candid);
	}
	
	@PutMapping("/candidate/updatecandidate")
	public ResponceStruture<Candidate> updateCandidate(@RequestParam int candid, @RequestBody CandidateRegisterDto candto) {
		return candidateServie.updateCandidate(candid,candto);
	}
	
	@PostMapping("/candidate/{candid}/addskill")
	public ResponceStruture<List<Skill>> addSkill(@PathVariable int candid,@RequestBody List<Skill> skill) {
		return candidateServie.addSkill(candid,skill);
	}
	
	@DeleteMapping("/candidate/{candid}/deleteskill/{skillid}")
	public void deleteCandidatesSkill(@PathVariable int candid,@PathVariable int skillid) {
		candidateServie.deleteCandidatesSkill(candid,skillid);
	}
	
	@GetMapping("/candidate/getskills")
	public ResponceStruture<List<Skill>> getSkillsByCandidateId(@RequestParam int candid) {
		return candidateServie.getSkillsByCandidateId(candid);
	}
	
	@GetMapping("/candidate/getprofile")
	public ResponceStruture<Candidate> getCandidateProfile(@RequestParam int candid) {
		return candidateServie.getCandidateProfile(candid);
	}
	
	@GetMapping("/candidate/getapplicationhistory")
	public ResponceStruture<List<ApplicationResponseDto>> getApplicationHistory(@RequestParam int candid) {
		return candidateServie.getAllApplications(candid);
	}
	
	@GetMapping("/candidate/application/shortlisted")
	public ResponceStruture<List<ApplicationResponseDto>> getShortlistedApplications(@RequestParam int candid) {
		return candidateServie.getShortlistedApplication(candid);
	}
	
	@GetMapping("/candidate/application/rejected")
	public ResponceStruture<List<ApplicationResponseDto>> getRejectedApplications(@RequestParam int candid) {
		return candidateServie.getRejectedApplication(candid);
	}
	
	@DeleteMapping("/candidate/withdrawapplication")
	public ResponceStruture<String> withdrawApplication(@RequestParam int candid,@RequestParam int appid) {
		return candidateServie.withdrawApplication(candid,appid);
	}
	
	@PostMapping("/candidate/addeducation")
	public ResponceStruture<List<Education>> addEducation(@RequestParam int candid,@RequestBody Education edu) {
	 return	candidateServie.addEducation(candid,edu);
	}
	
	@PostMapping("/candidate/addexperience")
	public ResponceStruture<List<Experience>> addExperience(@RequestParam int candid,@RequestBody Experience exp) {
	 return	candidateServie.addExperience(candid,exp);
	}
	
}
