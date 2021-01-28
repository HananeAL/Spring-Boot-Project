package com.project.main.services;

import com.project.main.models.Candidate;
import com.project.main.repositories.CandidateRepository;
import com.project.main.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
  
  @Autowired
  private CandidateRepository candidateRepository;
  @Autowired
  private UserRepository userRepository;

  public void signUp(Candidate candidate) {
    candidateRepository.save(candidate);
  }

  public boolean emailExists(String email) {
    return userRepository.findByEmail(email) != null;
  }
  
}
