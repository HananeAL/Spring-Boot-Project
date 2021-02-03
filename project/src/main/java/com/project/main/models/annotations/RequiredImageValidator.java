package com.project.main.models.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class RequiredImageValidator implements ConstraintValidator<RequiredImage, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    ImageValidator imageValidator = new ImageValidator();
    return !file.isEmpty() && imageValidator.isValid(file, context);
  }
  
}

