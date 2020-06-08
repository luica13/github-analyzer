package com.analyzer.api.controller.advice;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.analyzer.api.exception.GitHubAnalysisException;

@RestControllerAdvice
public class ApiControllerAdvice {

	@ExceptionHandler(GitHubAnalysisException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public HashMap<String, String> handleNoHandlerFound(GitHubAnalysisException e, WebRequest request) {
		HashMap<String, String> response = new HashMap<>();
		response.put("status", "fail");
		response.put("message", e.getMessage());
		return response;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public HashMap<String, String> handleException(WebRequest request, Exception e) {
		HashMap<String, String> response = new HashMap<>();
		response.put("message", e.getMessage());
		return response;
	}

}