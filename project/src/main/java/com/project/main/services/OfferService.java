package com.project.main.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.project.main.models.Company;
import com.project.main.models.Offer;
import com.project.main.models.User;
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

  public void saveOffer(Offer offer, Company company) {
    offer.setCreationDate(LocalDate.now());
    offer.setCompany(company);
    offerRepository.save(offer);

    offerSkillService.saveAll(offer.getOfferSkills(), offer);

    offerResponsibilityService.saveAll(offer.getOfferResponsibilities(), offer);

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

}