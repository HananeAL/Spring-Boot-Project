package com.project.main.models;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Company extends User {

  private Integer size;

  private Date foundationDate;

  @Lob
  @Column(columnDefinition = "BLOB")
  private byte[] logo;

  @Lob
  @Column(columnDefinition = "BLOB")
  private byte[] wallpaper;

  public Integer getSize() {
    return size;
  }

  public Company() {}

  public Company(CompanyForm companyForm) {
    super(companyForm);
    this.size = companyForm.getSize();
    this.foundationDate = companyForm.getFoundationDate();
    setLogo(companyForm.getLogo());
    setWallpaper(companyForm.getWallpaper());
  }

  private void setWallpaper(MultipartFile wallpaper) {
    try {
      this.wallpaper = wallpaper.getBytes();
    } catch (IOException e) {
    }
  }

  private void setLogo(MultipartFile logo) {
    try {
      this.logo = logo.getBytes();
    } catch(IOException e) {
    }
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

  public byte[] getLogo() {
    return logo;
  }

  public void setLogo(byte[] logo) {
    this.logo = logo;
  }

  public byte[] getWallpaper() {
    return wallpaper;
  }

  public void setWallpaper(byte[] wallpaper) {
    this.wallpaper = wallpaper;
  }

  
}
