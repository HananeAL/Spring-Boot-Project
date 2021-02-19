package com.project.main.repositories;

import com.project.main.models.Company;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    List<Company> findAll();

    List<Company> findByNameLike(String name);
}
