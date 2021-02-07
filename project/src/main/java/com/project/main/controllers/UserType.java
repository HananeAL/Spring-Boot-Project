package com.project.main.controllers;

import com.project.main.models.User;

public class UserType {
  public static final String CANDIDATE = "candidate";
  public static final String COMPANY = "company";

  public static String validateUserType(String userType) {
    if (userType == null)
      return CANDIDATE;
    else if (userType.equals(CANDIDATE) || userType.equals(COMPANY))
      return userType;
    else
      return CANDIDATE;
  }

  public static boolean isCandidate(String userType) {
    return CANDIDATE.equals(userType);
  }

  public static boolean isCompany(String userType) {
    return COMPANY.equals(userType);
  }

  public static boolean isCandidate(User user) {
    return CANDIDATE.equals(user.getUserType());
  }

  public static boolean isCompany(User user) {
    return COMPANY.equals(user.getUserType());
  }



}
