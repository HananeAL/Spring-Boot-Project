package com.project.main.controllers;

import com.project.main.models.CandidateForm;
import com.project.main.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	private CityRepository cityRepository;

	@GetMapping({ "/", "/home" })
	public String getHomePage() {
		return Views.HOME_PAGE;
	}

	@RequestMapping("/companies")
	public String getCompaniesPage() {
		return Views.COMPANIES_PAGE;
	}

	@RequestMapping("/company-page")
	public String getCompanyPage() {
		// get data and put it in the model
		return Views.COMPANY_PAGE;
	}

	@RequestMapping("/company-profile/{id}")
	public String getCompanyProfile(@PathVariable int id, Model model) {
		// get data put it in model
		return Views.COMPANY_PROFILE;
	}

	@RequestMapping("/signup")
	public String getSignUpForm(Model model) {
		model.addAttribute("candidateForm", new CandidateForm());
		return Views.SIGN_UP;
	}

	@GetMapping("add-skills")
	private String getAddSkillsPage() {
		return Views.ADD_SKILLS;
	}

/*	@GetMapping("/company/add-adresses")
	private String getAddCompanyAddrPage() {
		// get cities from DB
		cityRepository
		return Views.ADD_COMPANY_ADDRESSES;
	}*/

}
