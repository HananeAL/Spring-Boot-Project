package com.project.main.models;

import javax.persistence.Id;

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
