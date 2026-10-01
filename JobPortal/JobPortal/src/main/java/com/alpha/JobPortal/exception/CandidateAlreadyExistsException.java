package com.alpha.JobPortal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alpha.JobPortal.dto.ResponceStruture;

public class CandidateAlreadyExistsException extends RuntimeException{

	public CandidateAlreadyExistsException() {
		
	}
	
}
