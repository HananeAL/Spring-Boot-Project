package com.project.main.repositories;

import com.project.main.models.Candidate;

import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {

}
