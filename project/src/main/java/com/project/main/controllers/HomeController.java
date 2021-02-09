package com.project.main.controllers;

import com.project.main.models.CandidateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "/home" })
	public String getHomePage() {
		return Views.HOME_PAGE;
	}

	@RequestMapping("/companies")
	public String getCompaniesPage() {
		return Views.COMPANIES_PAGE;
	}

	// to be changed--> /company-page/{id}
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

}
