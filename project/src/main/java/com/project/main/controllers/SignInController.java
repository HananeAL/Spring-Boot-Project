package com.project.main.controllers;

import javax.servlet.http.HttpSession;

import com.project.main.models.User;
import com.project.main.services.CityService;
import com.project.main.services.SignInService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

  @Autowired
  private SignInService signInService;
  @Autowired
  private CityService cityService;

  @GetMapping("/signin")
  public String getSignInForm() {
    return Views.SIGN_IN_FORM;
  }

  @PostMapping("/signin")
  public String signIn(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {

    if (!signInService.isUser(email, password)) {
      model.addAttribute("error", "invalid email and/or password");
      return Views.SIGN_IN_FORM;
    } else {
      User user = signInService.getUser();
      session.setAttribute("user", user);
      if (UserType.isCandidate(user))
        return Views.HOME_PAGE; // whatever
      else {
        return Views.HOME_PAGE; // we can change it
      }
    }
  }

}
