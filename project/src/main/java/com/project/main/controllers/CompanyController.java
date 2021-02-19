package com.project.main.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.project.main.models.Company;
import com.project.main.models.CompanyForm;
import com.project.main.models.User;
import com.project.main.services.CityService;
import com.project.main.services.CompanyService;
import com.project.main.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CityService cityService;

    @Autowired
    private OfferService offerService;

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

    @GetMapping("/company/add-addresses")
    public String getAddCompanyAddrPage(Model model) {
        model.addAttribute("cities", cityService.getAll());
        return Views.ADD_COMPANY_ADDRESSES;
    }

    @GetMapping("/company/offers")
    public String getCompanyOffersPage(Model model, HttpSession session) {
        // we are sure the user is signed in and it's a company (thanks to filters)
        User user = (User) session.getAttribute("user");
        Company company = new Company(user);
        model.addAttribute("offers", offerService.getOffers(company));
        return Views.COMPANY_OFFERS_PAGE;
    }

    @RequestMapping("/companies")
    public String getAllCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return Views.COMPANIES_PAGE;
    }

    @RequestMapping("/company-page/{id}")
    public String getCompanyPage(@PathVariable int id, Model model) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return "redirect:/companies";
        } 
        else {
            model.addAttribute("company", company);
            return Views.COMPANY_PAGE;
        }
    }
}
