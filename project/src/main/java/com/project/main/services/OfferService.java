package com.project.main.services;

import java.time.LocalDate;
import java.util.List;
import com.project.main.models.Company;
import com.project.main.models.Offer;
import com.project.main.models.OfferForm;
import com.project.main.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

  @Autowired
  private OfferRepository offerRepository;

  @Autowired
  private OfferSkillService offerSkillService;

  @Autowired
  private OfferResponsibilityService offerResponsibilityService;

  public void saveOffer(OfferForm offerForm) {
    Offer offer = new Offer(offerForm);
    offerRepository.save(offer);

    offerSkillService.saveAll(offerForm.getSkills(), offer);

    offerResponsibilityService.saveAll(offerForm.getResponsibilities(), offer);
  }

  public List<Offer> getAll() {
    return offerRepository.findAll();
  }

  public List<Offer> getOffers(Company company) {
    return offerRepository.findByCompanyId(company.getId());
  }

  public int countOffers(Company company) {
    return offerRepository.countByCompanyId(company.getId());
  }

  public Offer getOffer(int id) {
    Offer offer = offerRepository.findById(id);
    return offer;
  }

}