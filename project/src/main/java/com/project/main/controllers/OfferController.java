package com.project.main.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.project.main.models.Company;
import com.project.main.models.Offer;
import com.project.main.models.OfferForm;
import com.project.main.models.OfferResponsibility;
import com.project.main.models.OfferSkill;
import com.project.main.models.User;
import com.project.main.services.CityService;
import com.project.main.services.OfferResponsibilityService;
import com.project.main.services.OfferService;
import com.project.main.services.OfferSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OfferController {

  @Autowired
  private OfferService offerService;

  @Autowired
  private CityService cityService;

  @Autowired
  private OfferSkillService offerSkillService;

  @Autowired
  private OfferResponsibilityService offerResponsibilityService;

  @GetMapping({ "/", "/home" })
  public String getOffers(@RequestParam(defaultValue = "") String speciality,
      @RequestParam(defaultValue = "") String city, @RequestParam(defaultValue = "") String type, Model model) {

    model.addAttribute("offers", offerService.getOffers(speciality, city, type));
    model.addAttribute("cities", offerService.getCities());
    model.addAttribute("s", speciality);
    return Views.HOME_PAGE;
  }

  @GetMapping("/company/post-offer")
  public String getPostOfferForm(Model model, HttpSession session) {

    Company company = getCompany(session);
    model.addAttribute("cities", cityService.getCities(company));
    return Views.POST_OFFER_FORM;
  }

  @PostMapping("/company/post-offer")
  @ResponseBody
  public void postOffer(@RequestBody OfferForm offerForm, HttpSession session) {

    Company company = getCompany(session);
    offerForm.setCompany(company);
    offerService.saveOffer(offerForm);
  }

  @GetMapping("/company/offers")
  public String getCompanyOffersPage(@RequestParam(defaultValue = "") String speciality,
  @RequestParam(defaultValue = "") String city, @RequestParam(defaultValue = "") String type, Model model, HttpSession session) {

    Company company = getCompany(session);
    model.addAttribute("offers", offerService.getOffers(company, speciality, city, type));
    model.addAttribute("cities", offerService.getCities(company));
    model.addAttribute("s",speciality);
    return Views.COMPANY_OFFERS_PAGE;
  }

  @GetMapping("/offers/{id}")
  @ResponseBody
  public Offer getOffer(@PathVariable int id) {

    Offer offer = offerService.getOffer(id);
    if (offer == null)
      return null;
    List<OfferSkill> skills = offerSkillService.getSkills(offer);
    List<OfferResponsibility> responsibilities = offerResponsibilityService.getResponsibilities(offer);
    OfferForm offerForm = new OfferForm(offer, skills, responsibilities);
    return offerForm;
  }

  @GetMapping("/offers/delete/{id}")
  public String deleteOffer(@PathVariable int id) {
    offerService.deletOffer(id);
    return "redirect:/company/offers";
  }



  // @GetMapping("/offers")
  // public String getOffers(@RequestParam(defaultValue = "") String speciality,
  // @RequestParam(defaultValue = "") String city, @RequestParam(defaultValue =
  // "") String type, Model model) {
  // System.out.println(speciality + ", " + city + ", " + type);
  // model.addAttribute("offers", offerService.getOffers(speciality, city, type));
  // return Views.HOME_PAGE; // ! we need to use this in both home and comp
  // profile
  // }

  private Company getCompany(HttpSession session) {

    User user = (User) session.getAttribute("user");
    Company company = new Company(user);
    return company;
  }

}