package com.project.main.repositories;

import java.util.List;

import com.project.main.models.Skill;
import com.project.main.models.SkillForm;

import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Integer> {

    //List<Skill> findByCandidateId(int id);

}
