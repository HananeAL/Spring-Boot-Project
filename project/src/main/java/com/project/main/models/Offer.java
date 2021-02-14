package com.project.main.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Offer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String type;

  private String speciality;

  private String position;

  private String city;

  private String description;

  // private short minExperience;

  // private short maxExperience;

  // private double minSalary;

  // private double maxSalary;

  private Date startDate;

  // private Date endDate;

  private Date creationDate;

  private Date closingDate;

  private boolean isPublic;

  private boolean receiveRecommendations;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  @OneToMany(mappedBy = "offer")
  private List<OfferSkill> offerSkills;

  @OneToMany(mappedBy = "offer")
  private List<OfferResponsibility> offerResponsibilities;

  public int getId() {
    return id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // public short getMinExperience() {
  //   return minExperience;
  // }

  // public void setMinExperience(short minExperience) {
  //   this.minExperience = minExperience;
  // }

  // public short getMaxExperience() {
  //   return maxExperience;
  // }

  // public void setMaxExperience(short maxExperience) {
  //   this.maxExperience = maxExperience;
  // }

  // public double getMinSalary() {
  //   return minSalary;
  // }

  // public void setMinSalary(double minSalary) {
  //   this.minSalary = minSalary;
  // }

  // public double getMaxSalary() {
  //   return maxSalary;
  // }

  // public void setMaxSalary(double maxSalary) {
  //   this.maxSalary = maxSalary;
  // }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  // public Date getEndDate() {
  //   return endDate;
  // }

  // public void setEndDate(Date endDate) {
  //   this.endDate = endDate;
  // }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getClosingDate() {
    return closingDate;
  }

  public void setClosingDate(Date closingDate) {
    this.closingDate = closingDate;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public boolean isReceiveRecommendations() {
    return receiveRecommendations;
  }

  public void setReceiveRecommendations(boolean receiveRecommendations) {
    this.receiveRecommendations = receiveRecommendations;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public List<OfferSkill> getOfferSkills() {
    return offerSkills;
  }

  public void setOfferSkills(List<OfferSkill> offerSkills) {
    this.offerSkills = offerSkills;
  }

  public List<OfferResponsibility> getOfferResponsibilities() {
    return offerResponsibilities;
  }

  public void setOfferResponsibilities(List<OfferResponsibility> offerResponsibilities) {
    this.offerResponsibilities = offerResponsibilities;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
