package com.project.main.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.main.models.Company;
import com.project.main.models.CompanyGeneralInfo;
import com.project.main.models.User;
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

    public List<Company> findByName(String name) {
        return companyRepository.findByNameLike(name + "%");
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

    public void saveCompany(CompanyGeneralInfo companyGeneralInfo, int id) {
        Company company1 = companyRepository.findById(id).get();
        company1.setName(companyGeneralInfo.getName());
        company1.setEmail(companyGeneralInfo.getEmail());
        companyRepository.save(company1);

    }

    public void changeName(int id, String name) {
        Company p = new Company();
        p = companyRepository.findById(id).get();
        p.setName(p.getName());
        companyRepository.save(p);
    }

    public CompanyGeneralInfo updateCompany(CompanyGeneralInfo company) {
        Company company1 = companyRepository.findById(company.getId()).get();
        company1.setId(company.getId());
        company1.setName(company.getName());
        company1.setEmail(company.getEmail());
        return companyRepository.save(company1);
    }

}
