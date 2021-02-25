package com.project.main.repositories;

import com.project.main.models.OfferSkill;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferSkillRepository extends CrudRepository<OfferSkill, Integer> {
    // List<OfferSkill> findNameAndLevelByOfferId(int id);
    List<OfferSkill> findAll();

    List<OfferSkill> findByOfferId(int id);
}
