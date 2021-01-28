package com.project.main.models;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import com.project.main.models.annotations.Image;
import com.project.main.models.annotations.RequiredFile;

import org.springframework.web.multipart.MultipartFile;

public class CompanyForm extends User {

  @PositiveOrZero(message = "size must be a positive integer")
  private Integer size;

  @NotNull(message = "foundation date is required")
  @PastOrPresent(message = "foundation date must be in the past or the present")
  private Date foundationDate;
  
  @RequiredFile
  @Image
  private MultipartFile logo;

  private MultipartFile wallpaper;

  public CompanyForm() {}
  
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

  public MultipartFile getLogo() {
    return logo;
  }

  public void setLogo(MultipartFile logo) {
    this.logo = logo;
  }

  public MultipartFile getWallpaper() {
    return wallpaper;
  }

  public void setWallpaper(MultipartFile wallpaper) {
    this.wallpaper = wallpaper;
  }

  
}
