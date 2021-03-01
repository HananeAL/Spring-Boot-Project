package com.project.main.repositories;

import java.util.List;
import com.project.main.models.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Integer> {

  List<Offer> findAll();

  List<Offer> findByCompanyId(int id);
  
  @Query("FROM Offer WHERE company_id = ?1 AND speciality LIKE ?2 AND city LIKE ?3 AND type LIKE ?4")
  List<Offer> search(int companyId, String speciality, String city, String type);

  int countByCompanyId(int id);

  Offer findById(int id);

  @Query("FROM Offer WHERE speciality LIKE ?1 AND city LIKE ?2 AND type LIKE ?3")
  List<Offer> search(String speciality, String city, String type);

  /* get cities where we have at least an offer */
  @Query("SELECT DISTINCT city FROM Offer")
  List<String> findCities();

  /* get cities where a company posted at least an offer */
  @Query("SELECT DISTINCT city FROM Offer WHERE company_id = ?1")
  List<String> findCities(int companyId);
}


