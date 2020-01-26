package com.qdesrame.openapi.diff.utils;

/** Created by adarsh.sharma on 07/01/18. */
public enum RefType {
  REQUEST_BODIES("requestBodies"),
  RESPONSES("responses"),
  PARAMETERS("parameters"),
  SCHEMAS("schemas"),
  HEADERS("headers"),
  SECURITY_SCHEMES("securitySchemes"),
  ;

  RefType(String name) {
    this.name = name;
  }

  private String name;

    public String getName() {
        return this.name;
    }
}
