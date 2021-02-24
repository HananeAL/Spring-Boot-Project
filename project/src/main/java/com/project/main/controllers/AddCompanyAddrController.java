package com.project.main.controllers;

import javax.servlet.http.HttpSession;
import com.project.main.models.Address;
import com.project.main.models.User;
import com.project.main.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddCompanyAddrController {

  @Autowired
  private AddressService addressService;

  @PostMapping("/signup/add-company-addresses")
  public void addAddr(@RequestBody Address[] addresses, HttpSession session) {

    User user = (User) session.getAttribute("user");
    if (user != null) {
      addressService.saveAll(addresses, user);
    } else { // we need a filter
      System.out.println("no user in session");
    }
  }

}
