package com.project.main.models;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Candidate extends User {

  private String firstName;

  private String lastName;

  @Lob
  @Column(columnDefinition = "LONGBLOB")
  private byte[] photo;

  @Lob
  @Column(columnDefinition = "LONGBLOB")
  private byte[] cv;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Adresse adresse;

  public Candidate() {}
  
  public Candidate(CandidateForm candidateForm) {
    super(candidateForm);
    this.firstName = candidateForm.getFirstName();
    this.lastName = candidateForm.getLastName();
    setPhoto(candidateForm.getPhoto());
    setCv(candidateForm.getCv());
  }

  private void setCv(MultipartFile cv) {
    try {
      this.cv = cv.getBytes();
    } catch (IOException e) {
    }
  }

  private void setPhoto(MultipartFile photo) {
    try {
      this.photo = photo.getBytes();
    } catch (IOException e) {
    }
  }

  public String getFirstName() {
    return firstName;
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

  public byte[] getPhoto() {
    return photo;
  }

  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  public byte[] getCv() {
    return cv;
  }

  public void setCv(byte[] cv) {
    this.cv = cv;
  }

  public Adresse getAdresse() {
    return adresse;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }
}
