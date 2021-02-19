package com.project.main.repositories;

import java.util.List;

import com.project.main.models.Offer;

import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
  
  List<Offer> findAll();

  List<Offer> findByCompanyId(int id);
}
