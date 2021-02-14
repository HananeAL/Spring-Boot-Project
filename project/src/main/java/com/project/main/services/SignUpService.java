package com.project.main.services;

import com.project.main.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
  
  // @Autowired
  // private CandidateRepository candidateRepository;
  @Autowired
  private UserRepository userRepository;
  // @Autowired
  // private CompanyRepository companyRepository;

  // public void signUp(Candidate candidate) {
  //   candidateRepository.save(candidate);
  // }

  // public void signUp(Company company) {
  //   companyRepository.save(company);
  // }

  public boolean emailExists(String email) {
    return userRepository.findByEmail(email) != null;
  }
  
}
