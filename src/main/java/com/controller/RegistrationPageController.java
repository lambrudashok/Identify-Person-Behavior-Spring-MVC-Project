package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationPageController {

	@RequestMapping("/registrationpage")
	public String registrationPageController() {  // registration page UI
		return "registrationpage";
	}
}
