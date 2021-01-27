package com.project.main.models.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class RequiredFileValidator implements ConstraintValidator<RequiredFile, MultipartFile> {
  
  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext cxt) {
    return file != null && !file.isEmpty() && file.getSize() != 0;
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