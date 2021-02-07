package com.project.main.controllers;

import javax.servlet.http.HttpSession;

import com.project.main.models.SkillForm;
import com.project.main.models.User;
import com.project.main.services.AddSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddSkillsController {

  @Autowired
  private AddSkillsService addSkillsService;

  @PostMapping("/signup/add-skills")
  public void addSkills(@RequestBody SkillForm[] skillsForm, HttpSession session) {

    User user = (User) session.getAttribute("user"); // !!!
    if (user == null) { // just for now, later we will add a filter
      System.out.println("no candidate in session");
      return;
    }
    addSkillsService.addSkills(skillsForm, user);
  }




}