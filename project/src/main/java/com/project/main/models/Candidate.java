package com.project.main.models;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import org.apache.tomcat.util.codec.binary.Base64;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Candidate extends CandidateGeneralInfo {

  private static final long serialVersionUID = 1L;

  @Lob
  @Column(columnDefinition = "LONGBLOB")
  private byte[] photo;

  @Lob
  @Column(columnDefinition = "LONGBLOB")
  private byte[] cv;

  public Candidate() {
  }

  public Candidate(User user) {
    setId(user.getId());
  }

  public Candidate(CandidateForm candidateForm) {
    super(candidateForm);
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

  public byte[] getPhoto() {
    return photo;
  }

  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  public String generateBase64Photo() {
    return Base64.encodeBase64String(this.getPhoto());
  }

  public byte[] getCv() {
    return cv;
  }

  public void setCv(byte[] cv) {
    this.cv = cv;
  }

}
