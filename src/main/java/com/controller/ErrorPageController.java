package com.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorPageController {
	
	// error page     its handle exception occur and web.xml configuration handle url error
	@ExceptionHandler(Exception.class)
	public String showErrorPage404() {
		return "errorpage";
	}
	
		
}
