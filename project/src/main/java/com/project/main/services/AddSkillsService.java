package com.project.main.services;

import java.util.List;

import com.project.main.models.Candidate;
import com.project.main.models.Skill;
import com.project.main.models.SkillForm;
import com.project.main.models.User;
import com.project.main.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddSkillsService {

  @Autowired
  private SkillRepository skillRepository;

  public void addSkills(SkillForm[] skillsForm, User user) {

    Skill[] skills = SkillForm.toSkills(skillsForm, user);
    for (Skill skill : skills) {
      skillRepository.save(skill);
    }
  }

  
  //public List<Skill> getSkills(User user) { return
  //skillRepository.findByUserId(user.getId()); }
  

}
