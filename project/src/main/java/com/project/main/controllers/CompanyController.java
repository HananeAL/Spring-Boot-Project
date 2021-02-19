package com.project.main.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.project.main.models.Address;
import com.project.main.models.Company;
import com.project.main.models.CompanyForm;
import com.project.main.models.User;
import com.project.main.models.Offer;
import com.project.main.services.AddressService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

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

    @GetMapping("company/add-addresses")
    public String getAddCompanyAddrPage(Model model) {
        model.addAttribute("cities", cityService.getAll());
        return Views.ADD_COMPANY_ADDRESSES;
    }

    @GetMapping("/company/profile")
    public String getCompanyProfile(Model model, HttpSession session) {
        // we are sure the user is signed in and it's a company (thanks to filters)
        User user = (User) session.getAttribute("user");
        model.addAttribute("company", user);
        return Views.COMPANY_PROFILE;
    }

    @RequestMapping("/companies")
    public String getAllCoympanies(Model model, @RequestParam(defaultValue = "") String name) {

        List<Company> companies = companyService.findByName(name);
        Map<Integer, Integer> map = new HashMap<>();
        for (Company company : companies) {
            map.put(company.getId(), offerService.countOffers(company));
        }
        model.addAttribute("companies", companies);
        model.addAttribute("nbrOffers", map);
        model.addAttribute("name", name);
        return Views.COMPANIES_PAGE;
    }

    @RequestMapping("/company-page/{id}")
    public String getCompanyPage(@PathVariable int id, Model model) {
        Company company = companyService.getCompanyById(id);
        List<Address> addresses = addressService.getAddresses(company);
        List<Offer> offers = offerService.getOffers(company);
        if (company == null) {
            return "redirect:/companies";
        } else {
            model.addAttribute("company", company);
            model.addAttribute("addresses", addresses);
            model.addAttribute("offers", offers);
            model.addAttribute("nbrOffers", offerService.countOffers(company));
            return Views.COMPANY_PAGE;
        }
    }
}
