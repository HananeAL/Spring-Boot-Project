package com.project.main.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SocialMedia {
  @Id
  private int id;

  private SocialMediaName name;

  public SocialMediaName getName() {
    return name;
  }

  public void setName(SocialMediaName name) {
    this.name = name;
  }
}
