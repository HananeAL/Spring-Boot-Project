package com.project.main.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.project.main.models.Candidate;
import com.project.main.models.CandidateForm;
import com.project.main.models.Skill;
import com.project.main.models.User;
import com.project.main.services.AddSkillsService;
import com.project.main.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CandidateController {

  @Autowired
  private CandidateService candidateService;

  @Autowired
  private AddSkillsService addSkillService;

  @GetMapping("/candidate/signup")
  public String getSignUpForm(Model model) {
    model.addAttribute("candidateForm", new CandidateForm());
    return Views.CANDIDATE_SIGN_UP_FORM;
  }

  @PostMapping("/candidate/signup")
  public String signUp(@Valid CandidateForm candidateForm, BindingResult result, HttpSession session) {

    if (!result.hasErrors()) {
      Candidate candidate = new Candidate(candidateForm);
      candidateService.signUp(candidate);
      session.setAttribute("user", (User) candidate);
      return "redirect:/candidate/add-skills";
    }
    return Views.CANDIDATE_SIGN_UP_FORM;
  }

  @GetMapping("/candidate/add-skills")
  public String getAddSkillsPage() {
    return Views.ADD_SKILLS;
  }

  @GetMapping("/candidate/profile")
  public String getcandidateProfile(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    // List<Skill> skills = addSkillService.getSkills(user);
    model.addAttribute("candidat", user);
    // model.addAttribute("skills", skills);
    return Views.CANDIDATE_PROFILE;
  }

}
