package com.project.main.models;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

  private LocalDate startDate;

  private LocalDate creationDate;

  private LocalDate closingDate;

  private boolean isPublic;

  private boolean receiveRecommendations;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  public Offer() {
  }

  public Offer(Offer offer) {
    setCity(offer.getCity());
    setSpeciality(offer.getSpeciality());
    setPosition(offer.getPosition());
    setDescription(offer.getDescription());
    setStartDate(offer.getStartDate());
    setCreationDate(LocalDate.now());
    setClosingDate(offer.getClosingDate());
    setPublic(offer.isPublic());
    setReceiveRecommendations(offer.isReceiveRecommendations());
    setCompany(offer.getCompany());
    setType(offer.getType());
  }

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

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public LocalDate getClosingDate() {
    return closingDate;
  }

  public void setClosingDate(LocalDate closingDate) {
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
