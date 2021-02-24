package com.project.main.models;

import java.io.IOException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import org.apache.tomcat.util.codec.binary.Base64;

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
    try {
      if (companyForm.getLogo() != null)
        setLogo(companyForm.getLogo().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      if (companyForm.getWallpaper() != null)
        setWallpaper(companyForm.getWallpaper().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Company(User user) {
    setId(user.getId());
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
