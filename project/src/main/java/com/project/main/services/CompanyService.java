package com.project.main.services;

import java.util.Optional;
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

    public void signUp(Company company) {
        companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        Optional<Company> optional = companyRepository.findById(id);
        Company company = null;
        if (optional.isPresent()) {
            company = optional.get();
        }
        return company;
    }

    /*
     * the same as getCompanyById(int), just wanted to change the name without
     * affecting existing code
     */
    public Company getCompany(int id) {
        return getCompanyById(id);
    }

}
