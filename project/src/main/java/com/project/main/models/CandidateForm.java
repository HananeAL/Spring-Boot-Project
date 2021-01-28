package com.project.main.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.project.main.models.annotations.Document;
import com.project.main.models.annotations.Image;
import com.project.main.models.annotations.RequiredFile;
import org.springframework.web.multipart.MultipartFile;


public class CandidateForm extends User {

  @Size(min = 2, max = 50, message = "length should be between {min} and {max}")
  @Pattern(regexp = "[A-Za-z]*", message = "invalid first name")
  private String firstName;

  @Size(min = 2, max = 50, message = "length should be between {min} and {max}")
  @Pattern(regexp = "[A-Za-z]*", message = "invalid last name")
  private String lastName;

  @RequiredFile
  @Image
  private MultipartFile photo;

  @RequiredFile
  @Document
  private MultipartFile cv;

  public String getFirstName() {
    return firstName;
  }

  public MultipartFile getPhoto() {
    return photo;
  }

  public void setPhoto(MultipartFile photo) {
    this.photo = photo;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public MultipartFile getCv() {
    return this.cv;
  }

  public void setCv(MultipartFile cv) {
    this.cv = cv;
  }

}
