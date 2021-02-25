package com.project.main.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

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

    public Company updateCompany(Company oldOne, Company newOne) {
        oldOne.setName(newOne.getName());
        oldOne.setEmail(newOne.getEmail());
        oldOne.setSite_web(newOne.getSite_web());
        oldOne.setFoundationDate(newOne.getFoundationDate());
        oldOne.setDescription(newOne.getDescription());
        oldOne.setSize(newOne.getSize());
        if (newOne.getLogo().length < 1) {
            oldOne.setLogo(oldOne.getLogo());
        } else {
            oldOne.setLogo(newOne.getLogo());
        }
        if (newOne.getWallpaper().length < 1) {
            oldOne.setWallpaper(oldOne.getWallpaper());
        } else {
            oldOne.setWallpaper(newOne.getWallpaper());
        }
        companyRepository.save(oldOne);
        return oldOne;
    }

    public boolean canUpdate(Company company, BindingResult errors) {
        if (errors.hasFieldErrors("email")) {
            if (errors.getFieldError("email").getDefaultMessage().equals("email already in use")) {
                System.out.println(true);
                if (company.getEmail().equals(errors.getFieldError("email").getRejectedValue()))
                    return true;
                else
                    return false;
            } else
                return false;
        }
        return true;
    }

}
