package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;

public class Endpoint {

  private String pathUrl;
  private PathItem.HttpMethod method;
  private String summary;

  private PathItem path;
  private Operation operation;

  @Override
  public String toString() {
    return method + " " + pathUrl;
  }

  public String getPathUrl() {
    return this.pathUrl;
  }

  public PathItem.HttpMethod getMethod() {
    return this.method;
  }

  public String getSummary() {
    return this.summary;
  }

  public PathItem getPath() {
    return this.path;
  }

  public Operation getOperation() {
    return this.operation;
  }

  public void setPathUrl(String pathUrl) {
    this.pathUrl = pathUrl;
  }

  public void setMethod(PathItem.HttpMethod method) {
    this.method = method;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public void setPath(PathItem path) {
    this.path = path;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }
}
