package com.project.main.models.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class DocumentValidator implements ConstraintValidator<Document, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    /*
     * RequiredFileValidator fileValidator = new RequiredFileValidator(); if
     * (!fileValidator.isValid(file, context)) // if file not present return true;
     */
    if (file == null || file.isEmpty())
      return true;
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    return isPdf(extension) || isWord(extension);
  }

  private boolean isWord(String extension) {
    return extension != null && extension.equals("docx");
  }

  private boolean isPdf(String extension) {
    return extension != null && extension.equals("pdf");
  }

}

/*
 * public class ContactNumberValidator implements
 * ConstraintValidator<ContactNumberConstraint, String> {
 * 
 * @Override public void initialize(ContactNumberConstraint contactNumber) { }
 * 
 * @Override public boolean isValid(String contactField,
 * ConstraintValidatorContext cxt) { return contactField != null &&
 * contactField.matches("[0-9]+") && (contactField.length() > 8) &&
 * (contactField.length() < 14); }
 * 
 * }
 */