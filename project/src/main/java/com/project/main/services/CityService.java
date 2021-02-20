package com.project.main.services;

import java.util.ArrayList;
import java.util.List;

import com.project.main.models.Address;
import com.project.main.models.City;
import com.project.main.models.Company;
import com.project.main.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  AddressService addressService;

  public List<City> getAll() {
    return cityRepository.findAll();
  }

  public List<City> getCities(Company company) {
    List<Address> addresses = addressService.getAddresses(company);
    return getCities(addresses);
  }

  /* get list of cities out of a list of addresses */
  public List<City> getCities(List<Address> addresses) {
    List<City> cities = new ArrayList<>();
    for (Address addr : addresses) {
      cities.add(addr.getCity());
    }
    return cities;
  }

}
