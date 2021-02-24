package com.project.main.models.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class RequiredDocumentValidator implements ConstraintValidator<RequiredDocument, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    DocumentValidator documentValidator = new DocumentValidator();
    return !file.isEmpty() && documentValidator.isValid(file, context);
  }
}
