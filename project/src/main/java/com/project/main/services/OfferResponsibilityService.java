package com.project.main.services;

import java.util.List;

import com.project.main.models.Offer;
import com.project.main.models.OfferResponsibility;
import com.project.main.repositories.OfferResponsibilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferResponsibilityService {
  
  @Autowired
  private OfferResponsibilityRepository offerRespRepository;

  public void saveAll(List<OfferResponsibility> offerResponsibilities, Offer offer) {
    for(OfferResponsibility responsibility: offerResponsibilities) {
      responsibility.setOffer(offer);
      offerRespRepository.save(responsibility);
    }
  }
}
