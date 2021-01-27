package com.project.main.controllers;

import javax.validation.Valid;

import com.project.main.models.Candidate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
  private static final String SIGN_UP_FORM = "sign_up_form";
  

  @GetMapping("/signup")
  public String getSignUpForm(Model model) {
    model.addAttribute("candidate", new Candidate());
    return SIGN_UP_FORM;
  }

  @PostMapping("/signup")
  public String signUp(@Valid Candidate candidate, BindingResult result) {
    
    return SIGN_UP_FORM;
  }
}
