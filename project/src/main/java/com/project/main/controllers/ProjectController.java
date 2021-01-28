package com.project.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

	private static final String HOME_PAGE = "home";
	private static final String COMPANIE_PAGE = "companies";

	@RequestMapping("/")
	public String Home(HttpServletRequest request) {
		return HOME_PAGE;
	}

	@RequestMapping("/companies")
	public String Companies(HttpServletRequest request) {
		return COMPANIE_PAGE;
	}
}
