package com.randob.crowdfunding_server.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author randobigor
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatabaseConstants {

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Public {
    public static final String PUBLIC_SCHEMA = "public";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Tables {
      public static final String CATEGORIES = "categories";
      public static final String USERS = "users";
      public static final String ROLES = "roles";
      public static final String USERS_TO_ROLES = "users_to_roles";
      public static final String PAYMENTS = "payments";
      public static final String PICTURES = "pictures";
      public static final String PROJECTS = "projects";
      public static final String PROJECTS_TO_PICTURES = "projects_to_pictures";
    }
  }
}
