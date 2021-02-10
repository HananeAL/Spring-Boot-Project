package com.project.main.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.main.services.CompanyService;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/companies")
    public String getAllCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return Views.COMPANIES_PAGE;
    }

    @RequestMapping("/company-page/{id}")
    public String getCompanyPage(@PathVariable int id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return Views.COMPANY_PAGE;
    }
}
