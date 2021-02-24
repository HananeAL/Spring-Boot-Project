package com.project.main.services;

import com.project.main.models.Candidate;
import com.project.main.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  public void signUp(Candidate candidate) {
    candidateRepository.save(candidate);
  }

}
