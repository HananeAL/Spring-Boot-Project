package com.project.main.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
public class CandidateGeneralInfo extends User {

  private static final long serialVersionUID = 1L;

  @Size(min = 2, max = 50, message = "length should be between {min} and {max}")
  @Pattern(regexp = "[A-Za-z]*", message = "invalid first name")
  private String firstName;

  @Size(min = 2, max = 50, message = "length should be between {min} and {max}")
  @Pattern(regexp = "[A-Za-z]*", message = "invalid last name")
  private String lastName;

  public CandidateGeneralInfo() {}

  public CandidateGeneralInfo(CandidateGeneralInfo candidateGeneralInfo) {
    super(candidateGeneralInfo);
    setFirstName(candidateGeneralInfo.getFirstName());
    setLastName(candidateGeneralInfo.getLastName());
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

}
