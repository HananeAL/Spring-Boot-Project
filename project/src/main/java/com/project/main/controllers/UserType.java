package com.project.main.controllers;

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
    return userType != null && userType.equals(CANDIDATE);
  }

  public static boolean isCompany(String userType) {
    return userType != null && userType.equals(COMPANY);
  }
}
