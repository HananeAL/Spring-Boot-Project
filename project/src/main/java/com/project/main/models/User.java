package com.project.main.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class User {
  
  @Id
  private int id;

  private String email;

  @Lob  @Column(columnDefinition="BLOB")
  private byte[] image;

  @Lob  @Column(columnDefinition = "TEXT")
  private String description;
  
  private String remeberMeCode;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRemeberMeCode() {
    return remeberMeCode;
  }

  public void setRemeberMeCode(String remeberMeCode) {
    this.remeberMeCode = remeberMeCode;
  }

 

  
}
