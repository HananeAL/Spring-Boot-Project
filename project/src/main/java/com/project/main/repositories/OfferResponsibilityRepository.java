package com.project.main.repositories;

import com.project.main.models.OfferResponsibility;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferResponsibilityRepository extends CrudRepository<OfferResponsibility, Integer> {
    
    List<OfferResponsibility> findByOfferId(int id);
}
