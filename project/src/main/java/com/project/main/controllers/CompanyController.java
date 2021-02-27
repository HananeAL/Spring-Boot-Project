package com.project.main.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.project.main.models.Address;
import com.project.main.models.Company;
import com.project.main.models.CompanyForm;
import com.project.main.models.Offer;
import com.project.main.models.User;
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
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/company/add-addresses")
    public String getAddCompanyAddrPage(Model model) {
        model.addAttribute("cities", cityService.getAll());
        return Views.ADD_COMPANY_ADDRESSES;
    }

    @PostMapping("/company/add-addresses")
    @ResponseBody
    public void addAddr(@RequestBody Address[] addresses, HttpSession session) {

        User user = (User) session.getAttribute("user");
        addressService.saveAll(addresses, user);
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

    @GetMapping("/company/profile")
    public String getCompanyProfile(Model model, HttpSession session) {
        // we are sure the user is signed in and it's a company (thanks to filters)
        User user = (User) session.getAttribute("user");
        List<Address> addresses = addressService.getAddresses(user);
        model.addAttribute("company", user);
        model.addAttribute("addresses", addresses);
        return Views.COMPANY_PROFILE;
    }

    @GetMapping("/company/profile/update")
    public String getCompanyProfileUpdate(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("companyForm", user); // !
        return Views.COMPANY_PROFILE_UPDATE;
    }

    @PostMapping("/company/profile/update")
    public String updateCompany(@Valid CompanyForm companyForm, BindingResult result, HttpSession session) {
        Company company = getCompany(session);
        if (!companyService.canUpdate(company, result)) {
            System.out.println("------error--------");
            return Views.COMPANY_PROFILE_UPDATE;
        }
        Company newOne = companyService.updateCompany(company, new Company(companyForm));
        session.setAttribute("user", newOne);
        return "redirect:/company/profile";
    }

    private Company getCompany(HttpSession session) {

        Company company = (Company) session.getAttribute("user");
        return company;
    }

}
