package com.project.main.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {
  @Id
  private int id;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
