package com.project.main.services;

import java.util.List;

import com.project.main.models.Candidate;
import com.project.main.models.Skill;
import com.project.main.repositories.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

  @Autowired
  private SkillRepository skillRepository;

  public List<Skill> getSkills(Candidate candidate) {
    return skillRepository.findByCandidateId(candidate.getId());
  }

}