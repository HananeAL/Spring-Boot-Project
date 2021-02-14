package com.project.main.repositories;

import java.util.List;

import com.project.main.models.Address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

  List<Address> findByUserId(int id);
}
