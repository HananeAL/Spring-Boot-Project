package com.project.main.services;

import com.project.main.models.User;
import com.project.main.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
  
  @Autowired
  private UserRepository userRepository;
  private User user; // represent user try to sign in

  public boolean isUser(String email, String password) {
    this.user = userRepository.findByEmailAndPassword(email, password);
    return this.user != null;
  }

  public User getUser() {
    return this.user;
  }

}
