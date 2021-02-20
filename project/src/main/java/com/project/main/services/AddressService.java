package com.project.main.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.project.main.models.Address;
import com.project.main.models.Company;
import com.project.main.models.City;
import com.project.main.models.User;
import com.project.main.repositories.AddressRepository;
import com.project.main.repositories.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private CityRepository cityRepository;

  public List<Address> getAddresses(User user) {
    return addressRepository.findByUserId(user.getId());
  }

  public void saveAll(Address[] addresses, User user) {
    Map<String, Integer> ids = getIds();
    for (Address a : addresses) {
      a.setUser(user);
      String cityName = a.getCity().getName();
      a.getCity().setId(ids.get(cityName)); // set the id of the object city inside object a
      addressRepository.save(a);
    }
  }

  /* return (cityName, id) of cities stored in database */
  private Map<String, Integer> getIds() {
    Map<String, Integer> ids = new HashMap<>();
    List<City> cities = cityRepository.findAll();
    for (City c : cities) {
      ids.put(c.getName(), c.getId());
    }
    return ids;
  }

  public List<Address> getAddresses(Company company) {
    return addressRepository.findByUserId(company.getId());
  }
  
}
