package com.project.main.repositories;

import com.project.main.models.Company;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
  
}
