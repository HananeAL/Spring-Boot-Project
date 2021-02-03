package com.project.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

	private static final String HOME_PAGE = "home";
	private static final String COMPANY_PAGE = "company";
	private static final String COMPANY_PROFILE = "company_profile";
	private static final String SIGNUP = "sign_up";

	@RequestMapping("/")
	public String Home(HttpServletRequest request) {
		return HOME_PAGE;
	}

	@RequestMapping("/company")
	public String Company(HttpServletRequest request) {
		return COMPANY_PAGE;
	}

	@RequestMapping("/companyprofile")
	public String CompanyProfile(HttpServletRequest request) {
		return COMPANY_PROFILE;
	}

	@RequestMapping("/sign_up")
	public String Signup(HttpServletRequest request) {
		return SIGNUP;
	}
}
