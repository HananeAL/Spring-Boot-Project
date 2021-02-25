package com.project.main.services;

import java.util.List;

import com.project.main.models.Offer;
import com.project.main.models.OfferSkill;
import com.project.main.repositories.OfferSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferSkillService {
  
  @Autowired
  private OfferSkillRepository offerSkillRepository;

  public void saveAll(List<OfferSkill> offerSkills, Offer offer) {
    for (OfferSkill skill: offerSkills) {
      skill.setOffer(offer);
      offerSkillRepository.save(skill);
    }
  }

  public List<OfferSkill> getAll() {
    return offerSkillRepository.findAll();
  }

  // get skills of an offer
  public List<OfferSkill> getSkills(Offer offer) {
    return offerSkillRepository.findByOfferId(offer.getId());
  }



















}
