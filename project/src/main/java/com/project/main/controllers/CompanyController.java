package com.project.main.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.project.main.models.Company;
import com.project.main.models.CompanyForm;
import com.project.main.models.User;
import com.project.main.services.CityService;
import com.project.main.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CityService cityService;


    @GetMapping("company/signup")
    public String getSignUpForm(Model model) {

      model.addAttribute("companyForm", new CompanyForm());
      return Views.COMPANY_SIGN_UP_FORM;
    }

    @PostMapping("/company/signup")
    public String signUp(@Valid CompanyForm companyForm, BindingResult result, HttpSession session) {

        if (!result.hasErrors()) {
            Company company = new Company(companyForm);
            companyService.signUp(company);
            session.setAttribute("user", (User) company);
            return "redirect:/company/add-addresses";
        }
        return Views.COMPANY_SIGN_UP_FORM;
    }

    @GetMapping("company/add-addresses")
    public String getAddCompanyAddrPage(Model model) {
        model.addAttribute("cities", cityService.getAll());
        return Views.ADD_COMPANY_ADDRESSES;
    }

    
    @GetMapping("/company/profile")
    public String getCompanyProfile(Model model, HttpSession session) {
        // we are sure the user is signed in and it's a company (thanks to filters)
        User user = (User)session.getAttribute("user");
        model.addAttribute("company", user);
        return Views.COMPANY_PROFILE;
    }


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