package com.alpha.JobPortal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.JobPortal.Repository.CompanyRepository;
import com.alpha.JobPortal.Repository.JobRepository;
import com.alpha.JobPortal.Repository.SkillsRepository;
import com.alpha.JobPortal.dto.CompanyRegisterDto;
import com.alpha.JobPortal.dto.CreateNewJobDto;
import com.alpha.JobPortal.dto.ResponceStruture;
import com.alpha.JobPortal.entity.Candidate;
import com.alpha.JobPortal.entity.Company;
import com.alpha.JobPortal.entity.Job;
import com.alpha.JobPortal.entity.Skill;
import com.alpha.JobPortal.exception.CommonException;
import com.alpha.JobPortal.exception.CompanyAlreadyExistsException;
import com.alpha.JobPortal.exception.CompanyNotExistsException;
import com.alpha.JobPortal.exception.JobNotFoundException;

@Service
public class CompanyService {

	
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private JobRepository jobRepository;
	
	public ResponceStruture<Company> CompanyRegister(CompanyRegisterDto compDto) {
		if(companyRepository.findByPhone(compDto.getPhone()).isPresent()) {
			throw new CompanyAlreadyExistsException();
		}
		Company c = new Company();	
		c.setName(compDto.getName());
		c.setEmail(compDto.getMail());
		c.setPhone(compDto.getPhone());
		c.setAddress(compDto.getAddress());
		c.setType(compDto.getCompType());
		c.setnoOfEmployee(compDto.getNoEmp());
		
		companyRepository.save(c);
		
		ResponceStruture<Company> respStru = new ResponceStruture<Company>();
		respStru.setStatuCode(HttpStatus.ACCEPTED.value());
		respStru.setMessage("Company Successfully Registered");
		respStru.setData(c);
		
		return respStru;

	}

	public ResponceStruture<String> CompanyDelete(int id) {
		if(!(companyRepository.findById(id).isPresent())) {
			throw new CompanyNotExistsException();
		}
		
		companyRepository.deleteById(id);
		ResponceStruture<String> rs= new ResponceStruture<String>();
		rs.setStatuCode(HttpStatus.OK.value());
		rs.setMessage("Comapany Delete Successfully");
		rs.setData("Comapany delete SucccessFully");
		return rs;
		}
		
	
	public ResponceStruture<Optional<Company>> FindCompany(int id) {
		if(!(companyRepository.findById(id).isPresent())) {
			throw new CompanyNotExistsException();
		}
		Optional<Company> company=companyRepository.findById(id);
		ResponceStruture<Optional<Company>> rs= new ResponceStruture<Optional<Company>>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Comapany Foound");
		rs.setData(company);
		return rs;
	}
		@Autowired
		private SkillsRepository skillRepository;
		
	public ResponceStruture<Job> createNewJob(CreateNewJobDto createjobDto) {
		
		Optional<Company> company = companyRepository.findById(createjobDto.getComid()); 
		
		if(company.isEmpty()) {
			throw new CompanyAlreadyExistsException();
		}
		Job job = new Job();
		job.setCompany(company.get());;
		job.setRole(createjobDto.getRole());		
		List<Skill> listSkills=new ArrayList<Skill>();
		if(createjobDto.getSkill() != null) {
			for(Skill sk : createjobDto.getSkill()) {
				Skill addskill = new Skill();
				addskill.setSkill(sk.getSkill());
				addskill.setJob(job);
				listSkills.add(addskill);
				
			}
		}
	    LocalDate today = LocalDate.now();
	    job.setPostDate(today.toString());
	    job.setJobDesc(createjobDto.getJobDesc());
	    job.setNoOfPosition(createjobDto.getNoOfPostion());
	    job.setSalary(createjobDto.getSalary());
	    job.setBond(createjobDto.getBond());
	    job.setReqExperince(createjobDto.getExperience());
	    job.setLastDate(createjobDto.getLastdatetoApply());
	    job.setReqQualifcation(createjobDto.getRedQualifcation());
	    job.setReqSkills(listSkills);
	    job.setStatus("Active");
		jobRepository.save(job);	
		
		ResponceStruture<Job> rs= new ResponceStruture<Job>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Comapany Foound");
		rs.setData(job);
		return rs;
	}
	
	public ResponceStruture<String> inactiveTheJob(int compid, int jobid) {
		Company company = companyRepository.findById(compid).orElseThrow(()-> new CompanyNotExistsException());
		Job job = jobRepository.findById(jobid).orElseThrow(()-> new JobNotFoundException());
		job.setStatus("inActive");
		
		
		jobRepository.save(job);
		ResponceStruture<String> rs= new ResponceStruture<String>();
		
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Company Inactivated");
		rs.setData("Company Inactivated");
		return rs;
	}

	public ResponceStruture<Job> repostjob(int comid, int jobid, String joblastdate) {
		Company company = companyRepository.findById(comid).orElseThrow(()-> new CompanyNotExistsException());
		Job oldjob = jobRepository.findById(jobid).orElseThrow(()-> new JobNotFoundException());
		
		if(oldjob.getCompany().getId()==company.getId()) {
			Job j = new Job();
			j.setCompany(company);
			j.setRole(oldjob.getRole());
			j.setJobDesc(oldjob.getJobDesc());
			j.setNoOfPosition(oldjob.getNoOfPosition());
			j.setSalary(oldjob.getSalary());
			j.setBond(oldjob.getBond());
			j.setReqExperince(oldjob.getReqExperince());
			j.setReqQualifcation(oldjob.getReqQualifcation());
			j.setReqSkills(new ArrayList<>(oldjob.getReqSkills()));
			j.setStatus("Active");
			j.setPostDate(LocalDate.now().toString());
			j.setLastDate(joblastdate);
			jobRepository.save(j);
			
			ResponceStruture<Job> rs= new ResponceStruture<Job>();
			rs.setStatuCode(HttpStatus.FOUND.value());
			rs.setMessage("Comapany Foound");
			rs.setData(j
					);
			return rs;
		}
		ResponceStruture<Job> rs= new ResponceStruture<Job>();
		rs.setStatuCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Comapany Not Found");
		rs.setData(null);
		return rs;
	}

	public ResponceStruture<Company> updateCompany(int comid, CompanyRegisterDto com) {
		Company company=companyRepository.findById(comid).orElseThrow(()-> new CompanyNotExistsException());
		company.setAddress(com.getAddress());
		company.setEmail(com.getMail());
		company.setPhone(com.getPhone());
		company.setName(com.getName());
		company.setType(com.getCompType());
		company.setnoOfEmployee(com.getNoEmp());
		companyRepository.save(company);
		
		ResponceStruture<Company> respStru = new ResponceStruture<Company>();
		respStru.setStatuCode(HttpStatus.ACCEPTED.value());
		respStru.setMessage("Company Successfully updated");
		respStru.setData(company);
		
		return respStru;
		
	}

	public ResponceStruture<List<Job>> getAllJobs(int comid) {
		Company company=companyRepository.findById(comid).orElseThrow(()-> new CompanyNotExistsException());
		List<Job> alljobs=jobRepository.findByCompany_Id(comid);
		
		ResponceStruture<List<Job>> rs=new ResponceStruture<List<Job>>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Jobs found are");
		rs.setData(alljobs);
		return rs;
		
	}

	public ResponceStruture<List<Job>> getJobStatus(int comid, String status) {
		Company company=companyRepository.findById(comid).orElseThrow(()-> new CompanyNotExistsException());
		List<Job> alljobs=jobRepository.findByCompany_IdAndStatusIgnoreCase(comid, status);
		ResponceStruture<List<Job>> rs=new ResponceStruture<List<Job>>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Jobs found are");
		rs.setData(alljobs);
		return rs;
	}

	public ResponceStruture<String> deleteJob(int comid, int jobid) {
		Job job=jobRepository.findByIdAndCompany_Id(jobid, comid);
		if(job==null) throw new CommonException(HttpStatus.NOT_FOUND.value(), "No Data Found");
		jobRepository.delete(job);
		
		ResponceStruture<String> rs= new ResponceStruture<String>();
		rs.setStatuCode(HttpStatus.OK.value());
		rs.setMessage("Job Delete Successfully");
		rs.setData("Job delete SucccessFully");
		return rs;

		
	}


	public ResponceStruture<Job> updateJob(int jobid, CreateNewJobDto job) {
		Job jobfound=jobRepository.findById(jobid).orElseThrow(()-> new JobNotFoundException());
		jobfound.setRole(job.getRole());
		jobfound.setReqSkills(job.getSkill());
		jobfound.setJobDesc(job.getJobDesc());
		jobfound.setNoOfPosition(job.getNoOfPostion());
		jobfound.setSalary(job.getSalary());
		jobfound.setBond(job.getBond());
		jobfound.setReqExperince(job.getExperience());
		jobfound.setLastDate(job.getLastdatetoApply());
		jobfound.setReqQualifcation(job.getRedQualifcation());
		
		jobRepository.save(jobfound);
		
		ResponceStruture<Job> rs= new ResponceStruture<Job>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("job updated sucessfully");
		rs.setData(jobfound);
		return rs;
		
		
		
	}

	public ResponceStruture<Job> activateJobStatus(int comid, int jobid) {
		Job job=jobRepository.findByIdAndCompany_Id(jobid, comid);
		if(job==null) throw new JobNotFoundException();
		job.setStatus("active");
		jobRepository.save(job);
		
		ResponceStruture<Job> rs= new ResponceStruture<Job>();
		rs.setStatuCode(HttpStatus.ACCEPTED.value());
		rs.setMessage("Status updated successfully");
		rs.setData(job);
		return rs;
		
	}

	public ResponceStruture<Job> updateJobDeadLine(int comid, int jobid, String deadline) {
		Job job=jobRepository.findByIdAndCompany_Id(jobid, comid);
		if(job==null) throw new JobNotFoundException();
		job.setLastDate(deadline);
		jobRepository.save(job);
		
		ResponceStruture<Job> rs= new ResponceStruture<Job>();
		rs.setStatuCode(HttpStatus.ACCEPTED.value());
		rs.setMessage("Deadline updated successfully");
		rs.setData(job);
		return rs;
	}


	
	
}
