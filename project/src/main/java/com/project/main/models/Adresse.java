package com.project.main.models;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Adresse {
  @Id
  private int id;

  private String street;

  @ManyToOne
  private City city;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
