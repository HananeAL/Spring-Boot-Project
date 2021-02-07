package com.project.main.services;

import java.util.List;
import com.project.main.models.City;
import com.project.main.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  public List<City> getAll() {
    return cityRepository.findAll();  
  }
  
}
