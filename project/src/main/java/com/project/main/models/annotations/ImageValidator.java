package com.project.main.models.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    /*RequiredFileValidator fileValidator = new RequiredFileValidator();
    // if file not present
    if (!fileValidator.isValid(file, context))  return true;*/
    if (file.isEmpty()) return true;
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    return isPng(extension) || isJpg(extension);
  }

  private boolean isJpg(String extension) {
    return extension != null && extension.equals("jpg");
  }

  private boolean isPng(String extension) {
    return extension != null && extension.equals("png");
  }


  
}
