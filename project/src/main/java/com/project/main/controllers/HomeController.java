package com.project.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private static final String HOME_PAGE = "home";
	private static final String COMPANIES_PAGE = "companies";

	@GetMapping({ "/", "/home" })
	public String getHomePage() {
		return HOME_PAGE;
	}

	@GetMapping("/companies")
	public String getCompaniesPage() {
		return COMPANIES_PAGE;
	}

	@GetMapping("add-skills")
	private String addSkills() {
		return "candidate_skills";
	}
}
