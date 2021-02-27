package com.project.main.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.project.main.models.Candidate;
import com.project.main.models.CandidateForm;
import com.project.main.models.Skill;
import com.project.main.models.User;
import com.project.main.services.CandidateService;
import com.project.main.services.SkillService;
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
  private SkillService skillService;

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
    Candidate candidate = (Candidate) session.getAttribute("user");
    List<Skill> skills = skillService.getSkills(candidate);
    model.addAttribute("candidat", candidate);
    model.addAttribute("skills", skills);
    return Views.CANDIDATE_PROFILE;
  }

  @GetMapping("/candidate/profile/update")
  public String getcandidateProfileUpdate(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    model.addAttribute("candidateForm", user); // !
    return Views.CANDIDATE_PROFILE_UPDATE;
  }

  @PostMapping("/candidate/profile/update")
  public String updateCandidate(@Valid CandidateForm candidateForm, BindingResult result, HttpSession session) {
    Candidate candidate = getCandidate(session);
    if (!candidateService.canUpdate(candidate, result)) {
      System.out.println("------error--------");
      return Views.CANDIDATE_PROFILE_UPDATE;
    }
    Candidate newOne = candidateService.updateCandidate(candidate, new Candidate(candidateForm));
    session.setAttribute("user", newOne);
    return "redirect:/candidate/profile";
  }

  private Candidate getCandidate(HttpSession session) {
    Candidate candidate = (Candidate) session.getAttribute("user");
    return candidate;
  }
}
