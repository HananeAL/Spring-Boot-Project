package com.project.main.services;

import com.project.main.models.Candidate;
import com.project.main.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  public void signUp(Candidate candidate) {
    candidateRepository.save(candidate);
  }

  public Candidate updateCandidate(Candidate oldOne, Candidate newOne) {
    oldOne.setFirstName(newOne.getFirstName());
    oldOne.setLastName(newOne.getLastName());
    oldOne.setEmail(newOne.getEmail());
    oldOne.setDescription(newOne.getDescription());
    if (newOne.getPhoto().length < 1) {
      oldOne.setPhoto(oldOne.getPhoto());
    } else {
      oldOne.setPhoto(newOne.getPhoto());
    }
    /*
     * if (newOne.getCv().length < 1) { oldOne.setPhoto(oldOne.getCv()); } else {
     * oldOne.setCv(newOne.getCv()); }
     */
    candidateRepository.save(oldOne);
    return oldOne;
  }

  public boolean canUpdate(Candidate candidate, BindingResult errors) {
    if (errors.hasFieldErrors("email")) {
      if (errors.getFieldError("email").getDefaultMessage().equals("email already in use")) {
        if (candidate.getEmail().equals(errors.getFieldError("email").getRejectedValue()))
          return true;
        else
          return false;
      } else
        return false;
    }
    return true;
  }
}
