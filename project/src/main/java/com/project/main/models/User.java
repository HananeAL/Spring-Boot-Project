package com.project.main.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
  
  @Id
  private int id;

  @NotBlank(message = "email is required")
  @Email(message = "email is invalid")
  @Max(value = 100, message = "max length is 100")
  private String email;

  @NotBlank
  @Min(value = 20, message = "min length is 20")
  @Lob  
  @Column(columnDefinition = "TEXT")
  private String description;
  
  private String remeberMeCode;

  @OneToMany
  @JoinColumn(name = "user_id")
  private List<SocialMedia> socialMedias = new ArrayList<SocialMedia>();

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
