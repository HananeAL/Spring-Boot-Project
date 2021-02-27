package com.project.main.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.project.main.controllers.UserType;
import com.project.main.models.annotations.UniqueEmail;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank(message = "email is required")
  @Email(message = "email is invalid")
  @UniqueEmail
  @Size(max = 100, message = "max length is {max}")
  @Column(unique = true)
  private String email;

  @Size(min = 6, max = 255, message = "length must be between {min} and {max}")
  private String password;

  @Size(min = 20, message = "min length is {min}")
  @Lob
  @Column(columnDefinition = "TEXT")
  private String description;

  private String remeberMeCode;

  private String userType;

  // ! to be deleted (like we did in Offer)
  // @OneToMany(mappedBy = "user")
  // private List<Address> addresses;

  public User() {
  }

  public User(User user) {
    setEmail(user.getEmail());
    setPassword(user.getPassword());
    setDescription(user.getDescription());
  }

  public User(CandidateGeneralInfo candidateGeneralInfo) {
    this((User) candidateGeneralInfo);
    setUserType(UserType.CANDIDATE);
  }

  public User(CompanyGeneralInfo companyGeneralInfo) {
    this((User) companyGeneralInfo);
    setUserType(UserType.COMPANY);
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  // public List<Address> getAddresses() {
  //   return addresses;
  // }
}
