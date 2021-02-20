package com.project.main.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import com.project.main.models.Company;
import com.project.main.models.Offer;
import com.project.main.models.User;
import com.project.main.services.CityService;
import com.project.main.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OfferController {

  @Autowired
  private OfferService offerService;

  @Autowired
  private CityService cityService;

  @GetMapping({ "/", "/home" })
  @ResponseBody
  public List<Offer> getOffers(Model model) {

    model.addAttribute("offers", offerService.getAll());
    return offerService.getAll();
  }

  @GetMapping("/company/post-offer")
  public String getPostOfferForm(Model model, HttpSession session) {

    Company company = getCompany(session);
    model.addAttribute("cities", cityService.getCities(company));
    return Views.POST_OFFER_FORM;
  }

  @PostMapping("/company/post-offer")
  @ResponseBody
  public void postOffer(@RequestBody Offer offer, HttpSession session) {

    Company company = getCompany(session);
    offerService.saveOffer(offer, company);
  }

  private Company getCompany(HttpSession session) {
    User user = (User) session.getAttribute("user");
    Company company = new Company(user);
    return company;
  }

}