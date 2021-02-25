package com.project.main.models;

import java.util.List;

/* we use it to get data from post-offer form */
public class OfferForm extends Offer {

  private List<OfferSkill> skills;

  private List<OfferResponsibility> responsibilities;

  public OfferForm() {}

  public OfferForm(Offer offer, List<OfferSkill> skills, List<OfferResponsibility> responsibilities) {
    
    super(offer);
    setSkills(skills);
    setResponsibilities(responsibilities);
  }

  public List<OfferSkill> getSkills() {
    return skills;
  }

  public void setSkills(List<OfferSkill> skills) {
    this.skills = skills;
  }

  public List<OfferResponsibility> getResponsibilities() {
    return responsibilities;
  }

  public void setResponsibilities(List<OfferResponsibility> responsibilities) {
    this.responsibilities = responsibilities;
  }
}