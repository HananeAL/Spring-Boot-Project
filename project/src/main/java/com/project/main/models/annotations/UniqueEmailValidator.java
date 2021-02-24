package com.project.main.models.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.project.main.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
  
  @Autowired 
  private UserRepository userRepository;
  
  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (userRepository == null) return true; // programming !!!
    return userRepository.findByEmail(email) == null;
  }
  
}

/*
public class ContactNumberValidator implements 
  ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
      ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+")
          && (contactField.length() > 8) && (contactField.length() < 14);
    }

}*/