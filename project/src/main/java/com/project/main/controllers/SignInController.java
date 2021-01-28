package com.project.main.controllers;

import com.project.main.services.SignInService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

  private static final String SIGN_IN_FORM = "sign_in_form";

  @Autowired
  private SignInService signInService;

  @GetMapping("/signin")
  public String getSignInForm() {
    return SIGN_IN_FORM;
  }

  @PostMapping("/signin")
  public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
    
    if (!signInService.isUser(email, password)) {
      model.addAttribute("error", "invalid email and/or password");
    }
    
    // additional conditions(if company ---> template + get the user ... 
    return SIGN_IN_FORM;
  }

}
