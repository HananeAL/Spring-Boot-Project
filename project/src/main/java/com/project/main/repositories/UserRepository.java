package com.project.main.repositories;

import com.project.main.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
  
  User findByEmail(String email);

  User findByEmailAndPassword(String email, String password);
}
