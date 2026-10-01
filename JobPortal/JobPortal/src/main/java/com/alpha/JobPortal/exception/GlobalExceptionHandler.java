package com.alpha.JobPortal.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.JobPortal.dto.ResponceStruture;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CandidateAlreadyExistsException.class)
	public ResponceStruture<String> alreadyExists() {

		ResponceStruture<String> response = new ResponceStruture<>();

		response.setStatuCode(HttpStatus.CONFLICT.value());
		response.setMessage("Candidate Already Exists");
		response.setData("Candidate Already Exists");

		return response;
	}

	@ExceptionHandler(CandidateNotExistsException.class)
	public ResponceStruture<String> notExists() {

		ResponceStruture<String> response = new ResponceStruture<>();

		response.setStatuCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Candidate Does Not Exist with Id");
		response.setData("Candidate Does Not Exist With Id");

		return response;
	}

	@ExceptionHandler(CommonException.class)
	public ResponceStruture<String> CommonException(CommonException e) {
		ResponceStruture<String> rs = new ResponceStruture<String>();
		rs.setStatuCode(e.getStatusCode());
		rs.setMessage(e.getMessage());
		rs.setData(e.getMessage());
		return rs;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		List<ObjectError> objectErrors=ex.getAllErrors();
		Map<String, String> responce=new HashMap<String, String>(); //or can directly use feild error like
		for (ObjectError objectError : objectErrors) {				//list<feilderror> feilderrors=ex.getbindingresult().getfeilderrors();
			FieldError feild=(FieldError)objectError;				//for(feilderror feild:feilderrors){
			responce.put(feild.getField(), feild.getDefaultMessage());//responce.put(feils.getfeild(),feild.getmessage()); }
			
		}
		return responce;
	}
	
	@ExceptionHandler(JobNotFoundException.class)
	public ResponceStruture<String> jobnotExists() {

		ResponceStruture<String> response = new ResponceStruture<>();

		response.setStatuCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("job Does Not Exist with Id");
		response.setData("job Does Not Exist With Id");

		return response;
	}
	
	@ExceptionHandler(ApplicationExistsException.class)
	public ResponceStruture<String> applicationExists() {

		ResponceStruture<String> response = new ResponceStruture<>();

		response.setStatuCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Application Exist with Id");
		response.setData("Apploication Exist With Id");

		return response;
	}
	
	@ExceptionHandler(ApplicationNotExixtsException.class)
	public ResponceStruture<String> applicationnotExists() {

		ResponceStruture<String> response = new ResponceStruture<>();

		response.setStatuCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Apploication Not Exist with Id");
		response.setData("Application Not Exist With Id");

		return response;
	}
	
	
	
	
	
	
	
	
	
}