package com.project.main.repositories;

import com.project.main.models.Skill;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
  
}
