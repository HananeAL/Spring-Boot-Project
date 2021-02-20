package com.project.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping("/addresses")
    public String getAddCompanyAddresses(Model model) {
        return Views.ADD_COMPANY_ADDRESSES;
    }
}
