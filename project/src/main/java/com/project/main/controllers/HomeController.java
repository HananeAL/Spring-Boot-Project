package com.project.main.controllers;

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

	@RequestMapping("/company-profile/{id}")
	public String getCompanyProfile(@PathVariable int id, Model model) {
		// get data put it in model
		return Views.COMPANY_PROFILE;
	}

}
