package com.project.main.controllers;

import com.project.main.repositories.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private static final String HOME_PAGE = "home";
	private static final String COMPANIES_PAGE = "companies";

	@Autowired
	private CityRepository cityRepository;

	@GetMapping({ "/", "/home" })
	public String getHomePage() {
		return HOME_PAGE;
	}

	@GetMapping("/companies")
	public String getCompaniesPage() {
		return COMPANIES_PAGE;
	}

	@GetMapping("add-skills")
	private String getAddSkillsPage() {
		return Views.ADD_SKILLS;
	}

	@GetMapping("/company/add-adresses")
	private String getAddCompanyAddrPage() {
		// get cities from DB
		cityRepository.
		return Views.ADD_COMPANY_ADDRESSES;
	}

}
