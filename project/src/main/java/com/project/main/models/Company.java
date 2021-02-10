package com.project.main.models;

import java.io.IOException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Company extends CompanyGeneralInfo {

  private static final long serialVersionUID = 1L;

  @Lob
  @Column(columnDefinition = "BLOB")
  private byte[] logo;

  @Lob
  @Column(columnDefinition = "BLOB")
  private byte[] wallpaper;

  public Company() {
  }

  public Company(CompanyForm companyForm) {
    super(companyForm);
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
    } catch (IOException e) {
    }
  }

  public byte[] getLogo() {
    return logo;
  }

  public String generateBase64Logo() {
    return Base64.encodeBase64String(this.getLogo());
  }

  public void setLogo(byte[] logo) {
    this.logo = logo;
  }

  public byte[] getWallpaper() {
    return wallpaper;
  }

  public String generateBase64Wallpaper() {
    return Base64.encodeBase64String(this.getWallpaper());
  }

  public void setWallpaper(byte[] wallpaper) {
    this.wallpaper = wallpaper;
  }

}
