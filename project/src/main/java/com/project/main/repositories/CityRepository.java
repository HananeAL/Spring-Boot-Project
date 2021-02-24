package com.project.main.repositories;

import java.util.List;

import com.project.main.models.City;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {
  
  List<City> findAll();
}

