package com.project.main.models;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

@MappedSuperclass
public class CompanyGeneralInfo extends User {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "company name is required")
  private String name;

  @PositiveOrZero(message = "size must be a positive integer")
  private Integer size;

  @NotNull(message = "foundation date is required")
  @PastOrPresent(message = "foundation date must be in the past or the present")
  private Date foundationDate;

  public CompanyGeneralInfo() {
  }

  public CompanyGeneralInfo(CompanyGeneralInfo companyGeneralInfo) {
    super(companyGeneralInfo);
    setName(companyGeneralInfo.getName());
    setSize(companyGeneralInfo.getSize());
    setFoundationDate(companyGeneralInfo.getFoundationDate());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Date getFoundationDate() {
    return foundationDate;
  }

  public void setFoundationDate(Date foundationDate) {
    this.foundationDate = foundationDate;
  }

}
