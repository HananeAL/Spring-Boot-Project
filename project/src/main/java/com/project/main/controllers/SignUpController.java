package com.project.main.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.project.main.models.Candidate;
import com.project.main.models.CandidateForm;
import com.project.main.models.Company;
import com.project.main.models.CompanyForm;
import com.project.main.models.User;
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

  @Autowired
  private SignUpService signUpService;

  @GetMapping({ "/signup", "/signup/{userType}" })
  public String getSignUpForm(@PathVariable(required = false) String userType, Model model) {

    if (UserType.isCompany(userType)) {
      model.addAttribute("companyForm", new CompanyForm());
      return Views.COMPANY_SIGN_UP_FORM;
    } else { // by default
      model.addAttribute("candidateForm", new CandidateForm());
      return Views.CANDIDATE_SIGN_UP_FORM;
    }
  }

  @PostMapping("/signup/candidate")
  public String signUp(@Valid CandidateForm candidateForm, BindingResult result, Model model, 
  HttpSession session) {
    
    if (!result.hasErrors()) {
      Candidate candidate = new Candidate(candidateForm);
      signUpService.signUp(candidate);
      session.setAttribute("user", (User)candidate); // !!!
      return Views.ADD_SKILLS;
    }
    return Views.CANDIDATE_SIGN_UP_FORM;
  }

  @PostMapping("/signup/company")
  public String signUp(@Valid CompanyForm companyForm, BindingResult result, Model model) {

    if (!result.hasErrors()) {
      Company company = new Company(companyForm);
      signUpService.signUp(company);
      model.addAttribute("status", "succes");
      // redirect to another view
    }

    return Views.COMPANY_SIGN_UP_FORM;
  }

}
