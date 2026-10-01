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

import com.alpha.JobPortal.dto.CompanyRegisterDto;
import com.alpha.JobPortal.dto.CreateNewJobDto;
import com.alpha.JobPortal.dto.ResponceStruture;
import com.alpha.JobPortal.entity.Company;
import com.alpha.JobPortal.entity.Job;
import com.alpha.JobPortal.service.CompanyService;

@RestController
@RequestMapping("/jobportal")
public class CompanyController {
	@Autowired
	private CompanyService compServ;
	
	@PostMapping("/company/register")
	public ResponceStruture<Company> CompanyRegister(@RequestBody CompanyRegisterDto compDto) {
		return compServ.CompanyRegister(compDto);
	}
	
	@DeleteMapping("/company/delete/{id}")
	public ResponceStruture<String> CompanyDelete(@PathVariable int id) {
		return compServ.CompanyDelete(id);
	}
	
	@GetMapping("/company/find/{id}")
	public ResponceStruture<Optional<Company>> FindCompany(@PathVariable int id) {
		return compServ.FindCompany(id);
	}
	
	@PostMapping("/company/createNewJob")
	public ResponceStruture<Job> createNewJob(@RequestBody CreateNewJobDto createjobDto) {
		return compServ.createNewJob(createjobDto);
	}
	
	@PostMapping("/company/job/repost")
	public ResponceStruture<Job> repostingjob(@RequestParam int comid, @RequestParam int jobid, @RequestParam String joblastdate) {
		return compServ.repostjob(comid,jobid,joblastdate);
	}
	
	@PostMapping("/company/job/inactive")
	public ResponceStruture<String> inactivejob(@RequestParam int compid, @RequestParam int jobid) {
		return compServ.inactiveTheJob(compid,jobid);
	}
	
	@PutMapping("/company/update")
	public ResponceStruture<Company> updateCompany(@RequestParam int comid,@RequestBody CompanyRegisterDto com) {
		return compServ.updateCompany(comid,com);
	}
	
	@GetMapping("/company/getjobs")
	public ResponceStruture<List<Job>> getAllJobs(@RequestParam int comid) {
		return compServ.getAllJobs(comid);
	}
	
	@GetMapping("/company/getstatus")
	public ResponceStruture<List<Job>> getjobstatus(@RequestParam int comid,@RequestParam String status) {
		return compServ.getJobStatus(comid,status);
	}
	@DeleteMapping("/company/deletejob")
	public ResponceStruture<String> deleteJob(@RequestParam int comid,@RequestParam int jobid) {
		return compServ.deleteJob(comid,jobid);
	}
	
	@PutMapping("/company/updatejob")
	public ResponceStruture<Job> updateJob(@RequestParam int jobid,@RequestBody CreateNewJobDto job) {
		return compServ.updateJob(jobid,job);
	}
	
	@PutMapping("/company/activatejob")
	public ResponceStruture<Job> activateJobStatus(@RequestParam int comid,@RequestParam int jobid) {
		return compServ.activateJobStatus(comid,jobid);
	}

	@PutMapping("/company/job/deadline")
	public ResponceStruture<Job> updateJobDeadline(@RequestParam int comid,@RequestParam int jobid,@RequestParam String deadline) {
		return compServ.updateJobDeadLine(comid,jobid,deadline);
	}

}
