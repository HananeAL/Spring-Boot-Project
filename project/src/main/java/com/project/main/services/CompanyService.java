package com.project.main.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.main.models.Company;

import com.project.main.repositories.CompanyRepository;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<Company>();
        for (Company company : companyRepository.findAll()) {
            companies.add(company);
        }
        return companies;
    }

}
