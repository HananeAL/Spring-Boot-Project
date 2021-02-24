package com.project.main.models;

import com.project.main.models.annotations.Image;
import com.project.main.models.annotations.RequiredFile;
import org.springframework.web.multipart.MultipartFile;


public class CompanyForm extends CompanyGeneralInfo {

  private static final long serialVersionUID = 1L;

  @RequiredFile
  @Image
  private MultipartFile logo;

  private MultipartFile wallpaper;
  
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
