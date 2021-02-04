package com.project.main.models;

import com.project.main.models.annotations.Document;
import com.project.main.models.annotations.Image;
import com.project.main.models.annotations.RequiredFile;

import org.springframework.web.multipart.MultipartFile;


public class CandidateForm extends CandidateGeneralInfo {

  private static final long serialVersionUID = 1L;

  @RequiredFile
  @Image
  private MultipartFile photo;

  @RequiredFile
  @Document
  private MultipartFile cv;

  public MultipartFile getPhoto() {
    return photo;
  }

  public void setPhoto(MultipartFile photo) {
    this.photo = photo;
  }

  public MultipartFile getCv() {
    return this.cv;
  }

  public void setCv(MultipartFile cv) {
    this.cv = cv;
  }

}
