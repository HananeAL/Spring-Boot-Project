package com.project.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.main.services.CompanyService;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/companies")
    public String getAllCompanies(HttpServletRequest request) {
        request.setAttribute("companies", companyService.getAllCompanies());
        return Views.COMPANIES_PAGE;
    }
}
