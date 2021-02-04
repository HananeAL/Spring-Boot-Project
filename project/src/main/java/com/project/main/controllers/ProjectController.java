package com.project.main.controllers;

import com.project.main.models.CandidateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

	@RequestMapping("/")
	public String getHomePage() {
		return Views.HOME;
	}

	@RequestMapping("/company")
	public String getCompanyPage() {
		return Views.COMPANY_PAGE;
	}

	@RequestMapping("/companyprofile")
	public String getCompanyProfile() {
		return Views.COMPANY_PROFILE;
	}

	@RequestMapping("/sign_up")
	public String getSignUpForm(Model model) {
		model.addAttribute("candidateForm", new CandidateForm());
		return Views.SIGN_UP;
	}
	
}
