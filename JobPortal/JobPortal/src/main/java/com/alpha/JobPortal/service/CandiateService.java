package com.alpha.JobPortal.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alpha.JobPortal.Repository.ApplicationRepo;
import com.alpha.JobPortal.Repository.CandidateRepository;
import com.alpha.JobPortal.Repository.JobRepository;
import com.alpha.JobPortal.Repository.SkillsRepository;
import com.alpha.JobPortal.dto.ApplicationResponseDto;
import com.alpha.JobPortal.dto.CandidateRegisterDto;
import com.alpha.JobPortal.dto.ResponceStruture;
import com.alpha.JobPortal.dto.SearchJobDto;
import com.alpha.JobPortal.entity.Application;
import com.alpha.JobPortal.entity.Candidate;
import com.alpha.JobPortal.entity.Education;
import com.alpha.JobPortal.entity.Experience;
import com.alpha.JobPortal.entity.Job;
import com.alpha.JobPortal.entity.Skill;
import com.alpha.JobPortal.exception.ApplicationNotExixtsException;
import com.alpha.JobPortal.exception.ApplicationNotExixtsException;
import com.alpha.JobPortal.exception.CandidateAlreadyExistsException;
import com.alpha.JobPortal.exception.CandidateNotExistsException;

import com.alpha.JobPortal.exception.CommonException;
import com.alpha.JobPortal.exception.JobNotFoundException;


@Service
public class CandiateService {
	@Autowired
	private CandidateRepository candrepo;
	@Autowired
	private JobRepository jobrepo;
	@Autowired
	private ApplicationRepo applicationrepo;
	@Autowired
	private SkillsRepository skillrepo;

	public ResponceStruture<Candidate> candidateRegister(CandidateRegisterDto candDto) {
	    if (candrepo.findByPhone(candDto.getPhone()).isPresent()) {
	        throw new CandidateAlreadyExistsException();
	    }
	    Candidate c = new Candidate();
	    c.setName(candDto.getName());
	    c.setEmail(candDto.getMail());
	    c.setPhone(candDto.getPhone());
	    c.setAge(candDto.getAge());
	    c.setGender(candDto.getGender());
	    candrepo.save(c);
	    
	    ResponceStruture<Candidate> respStru = new ResponceStruture<>();
	    respStru.setStatuCode(HttpStatus.ACCEPTED.value());
	    respStru.setMessage("Candidate Successfully Registered");
	    respStru.setData(c);
	    return respStru;
	}

	public ResponceStruture<String> candidateDelete(int id) {
	    if (!(candrepo.findById(id).isPresent())) {
	        throw new CandidateNotExistsException();
	    }
		candrepo.deleteById(id);
		ResponceStruture<String> respStru = new ResponceStruture<String>();
		respStru.setStatuCode(HttpStatus.OK.value());
		respStru.setMessage("Candidate Deleted Successfully");
		respStru.setData("Candidate Deleted Successfully");
		return respStru;
	}

	public ResponceStruture<Candidate> candidateFind(int id) {
	    if (!(candrepo.findById(id).isPresent())) {
	        throw new CandidateNotExistsException();
	    }
		Candidate can=candrepo.findById(id).orElseThrow(()->new CandidateNotExistsException());
		ResponceStruture<Candidate> rs = new ResponceStruture<Candidate>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Candidate Foound With the ID");
		rs.setData(can);
		return rs;
	}

	public ResponceStruture<List<SearchJobDto>> findBySkey(String skey) {
		List<SearchJobDto> result=new ArrayList<SearchJobDto>();
		List<Job> alljobs=jobrepo.findByRoleContainingIgnoreCaseOrReqSkills_SkillContainingIgnoreCase(skey, skey);
		
		if(alljobs.isEmpty()) throw new JobNotFoundException();
		
		for(Job job : alljobs) {
			
				SearchJobDto searchjobdto=new SearchJobDto();
				searchjobdto.setId(job.getId());
				searchjobdto.setRole(job.getRole());
				searchjobdto.setReqSkills(job.getReqSkills());
				searchjobdto.setJobDesc(job.getJobDesc());
				searchjobdto.setNoOfPosition(job.getNoOfPosition());
				searchjobdto.setSalary(job.getSalary());
				searchjobdto.setBond(job.getBond());;
				searchjobdto.setCompany(job.getCompany());
				searchjobdto.setReqExperince(job.getReqExperince());
				searchjobdto.setPostDate(job.getPostDate());
				searchjobdto.setLastDate(job.getLastDate());
				searchjobdto.setReqQualifcation(job.getReqQualifcation());
				searchjobdto.setStatus(job.getStatus());
				result.add(searchjobdto);
			}
			
		
		ResponceStruture<List<SearchJobDto>> rs=new ResponceStruture<List<SearchJobDto>>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Jobs Found By "+skey);
		rs.setData(result);
		return rs;

	}

	public ResponceStruture<Application> applicationForJob(int jobid, int candid) {
		if(applicationrepo.existsByJobIdAndCandidateId(jobid, candid)) throw new CommonException(HttpStatus.BAD_REQUEST.value(), "Application already exists");
		Job job=jobrepo.findById(jobid).orElseThrow(()-> new JobNotFoundException());
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		if(!(job.getStatus().toLowerCase().equals("active"))) throw new JobNotFoundException();
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate lastdate=LocalDate.parse(job.getLastDate(),formatter);
		if(lastdate.isBefore(LocalDate.now())) throw new ApplicationNotExixtsException();
		Application application=new Application();
		application.setCandidate(candidate);
		application.setApplicationdate(LocalDate.now().toString());
		application.setJob(job);
		application.setStatus("Applied");
		applicationrepo.save(application);
		ResponceStruture<Application> rs=new ResponceStruture<Application>();
		rs.setStatuCode(HttpStatus.ACCEPTED.value());
		rs.setMessage("Applied Successfully");
		rs.setData(application);
		return rs;
		
	
	
	
	}

	public ResponceStruture<String> checkApplicationStatus(int candid, int appid) {
		Optional<Application> application=applicationrepo.findByIdAndCandidateId(appid, candid);
		if(application.isEmpty()) throw  new ApplicationNotExixtsException();
		ResponceStruture<String> rs=new ResponceStruture<String>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Application Found");
		rs.setData(application.get().getStatus());
		return rs;
	}

	public ResponceStruture<List<ApplicationResponseDto>> getAllApplications(int candid) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		Optional<List<Application>> allapplications=applicationrepo.findByCandidateId(candid);
		if(allapplications.isEmpty()) throw new ApplicationNotExixtsException();
		List<ApplicationResponseDto> appres=new ArrayList<ApplicationResponseDto>();
		for(Application app: allapplications.get()) {
			ApplicationResponseDto resdto=new ApplicationResponseDto();
			resdto.setId(app.getId());
			resdto.setCandidate(app.getCandidate());
			resdto.setStatus(app.getStatus());
			resdto.setApplicationdate(app.getApplicationdate());
			appres.add(resdto);
		}
		
		ResponceStruture<List<ApplicationResponseDto>> rs=new ResponceStruture<List<ApplicationResponseDto>>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Applications Found");
		rs.setData(appres);
		return rs;
	}

	public ResponceStruture<Candidate> updateCandidate(int candid, CandidateRegisterDto candto) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		candidate.setName(candto.getName());
		candidate.setEmail(candto.getMail());
		candidate.setPhone(candto.getPhone());
		candidate.setAge( candto.getAge());
		candidate.setGender(candto.getGender());
		candrepo.save(candidate);
		ResponceStruture<Candidate> respStru = new ResponceStruture<>();
	    respStru.setStatuCode(HttpStatus.ACCEPTED.value());
	    respStru.setMessage("Candidate Successfully Updated");
	    respStru.setData(candidate);
	    return respStru;
		
	}

	public ResponceStruture<List<Skill>> addSkill(int candid,List<Skill> skill) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		List<Skill> newskills=candidate.getSkills();
		for (Skill skill2 : skill) {
			newskills.add(skill2);
		}
		candidate.setSkills(newskills);
		candrepo.save(candidate);
		ResponceStruture<List<Skill>> respStru = new ResponceStruture<>();
	    respStru.setStatuCode(HttpStatus.ACCEPTED.value());
	    respStru.setMessage("Skills Successfully Updated");
	    respStru.setData(candidate.getSkills());
	    return respStru;
		
		
	}

	public void deleteCandidatesSkill(int candid, int skillid) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		candidate.getSkills().removeIf(skill->skill.getId()==skillid);
		candrepo.save(candidate);
	}

	public ResponceStruture<List<Skill>> getSkillsByCandidateId(int candid) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		ResponceStruture<List<Skill>> respStru = new ResponceStruture<>();
	    respStru.setStatuCode(HttpStatus.ACCEPTED.value());
	    respStru.setMessage("Skills Successfully Updated");
	    respStru.setData(candidate.getSkills());
	    return respStru;
	}

	public ResponceStruture<Candidate> getCandidateProfile(int candid) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		ResponceStruture<Candidate> respStru = new ResponceStruture<>();
	    respStru.setStatuCode(HttpStatus.ACCEPTED.value());
	    respStru.setMessage("Skills Successfully Updated");
	    respStru.setData(candidate);
	    return respStru;
		
	}

	public ResponceStruture<List<ApplicationResponseDto>> getShortlistedApplication(int candid) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		List<Application> allapplications=applicationrepo.findByCandidateIdAndStatusIgnoreCase(candid, "shortlisted");
		if(allapplications.isEmpty()) throw new ApplicationNotExixtsException();
		List<ApplicationResponseDto> appres=new ArrayList<ApplicationResponseDto>();
		for(Application app: allapplications) {
			ApplicationResponseDto resdto=new ApplicationResponseDto();
			resdto.setId(app.getId());
			resdto.setCandidate(app.getCandidate());
			resdto.setStatus(app.getStatus());
			resdto.setApplicationdate(app.getApplicationdate());
			appres.add(resdto);
		}
		
		ResponceStruture<List<ApplicationResponseDto>> rs=new ResponceStruture<List<ApplicationResponseDto>>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Applications Found");
		rs.setData(appres);
		return rs;
		
		
	}

	public ResponceStruture<List<ApplicationResponseDto>> getRejectedApplication(int candid) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		List<Application> allapplications=applicationrepo.findByCandidateIdAndStatusIgnoreCase(candid, "rejected");
		if(allapplications.isEmpty()) throw new ApplicationNotExixtsException();
		List<ApplicationResponseDto> appres=new ArrayList<ApplicationResponseDto>();
		for(Application app: allapplications) {
			ApplicationResponseDto resdto=new ApplicationResponseDto();
			resdto.setId(app.getId());
			resdto.setCandidate(app.getCandidate());
			resdto.setStatus(app.getStatus());
			resdto.setApplicationdate(app.getApplicationdate());
			appres.add(resdto);
		}
		
		ResponceStruture<List<ApplicationResponseDto>> rs=new ResponceStruture<List<ApplicationResponseDto>>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Applications Found");
		rs.setData(appres);
		return rs;
	}

	public ResponceStruture<String> withdrawApplication(int candid, int appid) {
		Application application=applicationrepo.findByIdAndCandidateId(appid, candid).orElseThrow(()-> new ApplicationNotExixtsException());
		applicationrepo.delete(application);
		ResponceStruture<String> rs=new ResponceStruture<>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("Applications has been WithDrawen");
		rs.setData("Applications has been WithDrawen");
		return rs;
	}

	public ResponceStruture<List<Education>> addEducation(int candid, Education edu) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		List<Education> educationlist=candidate.getEducation();
		if(educationlist == null) educationlist=new ArrayList<Education>();
		educationlist.add(edu);
		candrepo.save(candidate);
		ResponceStruture<List<Education>> rs=new ResponceStruture<>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("education details have been added to id: "+candid);
		rs.setData(candidate.getEducation());
		return rs;
		
	}

	public ResponceStruture<List<Experience>> addExperience(int candid, Experience exp) {
		Candidate candidate=candrepo.findById(candid).orElseThrow(()-> new CandidateNotExistsException());
		List<Experience> experiencelist=candidate.getExperience();
		if(experiencelist == null) experiencelist=new ArrayList<Experience>();
		experiencelist.add(exp);
		candrepo.save(candidate);
		ResponceStruture<List<Experience>> rs=new ResponceStruture<>();
		rs.setStatuCode(HttpStatus.FOUND.value());
		rs.setMessage("experience details have been added to id: "+candid);
		rs.setData(candidate.getExperience());
		return rs;
	}

}
