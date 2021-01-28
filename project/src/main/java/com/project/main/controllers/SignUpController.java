package com.project.main.controllers;

import javax.validation.Valid;

import com.project.main.models.Candidate;
import com.project.main.models.CandidateForm;
import com.project.main.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
  private static final String CANDIDATE_SIGN_UP_FORM = "candidate_sign_up_form";
  private static final String COMPANY_SIGN_UP_FORM = "company_sign_up_form";

  @Autowired
  private SignUpService signUpService;

  @GetMapping({"/signup", "/signup/{userType}"})
  public String getSignUpForm(@PathVariable(required = false) String userType, Model model) {

    if (UserType.isCompany(userType)) {
      //model.addAttribute("companyForm", new companyForm());
      return COMPANY_SIGN_UP_FORM;
    } else { // by default
      model.addAttribute("candidateForm", new CandidateForm());
      return CANDIDATE_SIGN_UP_FORM;
    }
  }

  @PostMapping("/signup/candidate")
  public String signUp(@Valid CandidateForm candidateForm, BindingResult result, Model model) {

    if (!result.hasErrors()) {
      Candidate candidate = new Candidate(candidateForm);
      signUpService.signUp(candidate);
      model.addAttribute("status", "succes");
      // redirect to another view
    }

    return CANDIDATE_SIGN_UP_FORM;
  }

  /*@PostMapping("/signup/company")
  public String signUp(@Valid CompanyForm companyForm, BindingResult result, Model model) {

    if (!result.hasErrors()) {
      Company company = new Company(companyForm);
      signUpService.signUp(company);
      model.addAttribute("status", "succes");
      // redirect to another view
    }

    return COMPANY_SIGN_UP_FORM;
  }*/

}
