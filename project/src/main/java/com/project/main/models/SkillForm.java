package com.project.main.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/* this class is used to collecte skills from the form */
@MappedSuperclass
public class SkillForm {

  @Size(max = 50, message = "max length is {max}")
  @NotBlank(message = "skill name is required")
  private String name;

  @NotBlank(message = "skill level is required")
  private String level;

  public SkillForm() {}
  
  public SkillForm(SkillForm skillForm) {
    this.name = skillForm.name;
    this.level = skillForm.level;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  /* convert array of SkillForm objects to array of Skill objects */ 
  public static Skill[] toSkills(SkillForm[] skillForms, User user) {
    Skill[] skills = new Skill[skillForms.length];
    Candidate candidate = new Candidate(user);
    for (int i = 0; i < skillForms.length; i++) {
      skills[i] = new Skill(skillForms[i], candidate);
    }
    return skills;
  }

}
