package com.project.main.models;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.project.main.models.annotations.Document;
import com.project.main.models.annotations.Image;
import com.project.main.models.annotations.RequiredFile;
import org.springframework.web.multipart.MultipartFile;

//@Entity
public class Candidate extends User {

  @NotBlank(message = "first name is required")
  @Size(min = 2, max = 50, message = "length should be between 2 and 50")
  private String firstName;

  @NotBlank(message = "last name is required")
  @Size(min = 2, max = 50, message = "length should be between 2 and 50")
  private String lastName;

  @RequiredFile(message = "photo is required")
  @Image(message = "photo has to be in jpg or png format")
  private MultipartFile photo;

  @RequiredFile(message = "cv is required")
  @Document(message = "cv has to be in pdf or word format")
  private MultipartFile cv;

  @ManyToOne
  @JoinColumn(name = "adresse_id")
  private Adresse adresse;

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

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

}
