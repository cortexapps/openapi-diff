package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static com.qdesrame.openapi.diff.model.Changed.result;

public class ChangedOperation implements ComposedChanged {
  private Operation oldOperation;
  private Operation newOperation;

  private String pathUrl;
  private PathItem.HttpMethod httpMethod;
  private ChangedMetadata summary;
  private ChangedMetadata description;
  private boolean deprecated;
  private ChangedParameters parameters;
  @Nullable private ChangedRequestBody requestBody;
  private ChangedApiResponse apiResponses;
  private ChangedSecurityRequirements securityRequirements;
  private ChangedExtensions extensions;

  public ChangedOperation(
      String pathUrl,
      PathItem.HttpMethod httpMethod,
      Operation oldOperation,
      Operation newOperation) {
    this.httpMethod = httpMethod;
    this.pathUrl = pathUrl;
    this.oldOperation = oldOperation;
    this.newOperation = newOperation;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Arrays.asList(
        summary,
        description,
        parameters,
        requestBody,
        apiResponses,
        securityRequirements,
        extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    // TODO BETTER HANDLING FOR DEPRECIATION
    if (deprecated) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.NO_CHANGES;
  }

  public DiffResult resultApiResponses() {
    return result(apiResponses);
  }

  public DiffResult resultRequestBody() {
    return requestBody == null ? DiffResult.NO_CHANGES : requestBody.isChanged();
  }

    public Operation getOldOperation() {
        return this.oldOperation;
    }

    public Operation getNewOperation() {
        return this.newOperation;
    }

    public String getPathUrl() {
        return this.pathUrl;
    }

    public PathItem.HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public ChangedMetadata getSummary() {
        return this.summary;
    }

    public ChangedMetadata getDescription() {
        return this.description;
    }

    public boolean isDeprecated() {
        return this.deprecated;
    }

    public ChangedParameters getParameters() {
        return this.parameters;
    }

    @Nullable
    public ChangedRequestBody getRequestBody() {
        return this.requestBody;
    }

    public ChangedApiResponse getApiResponses() {
        return this.apiResponses;
    }

    public ChangedSecurityRequirements getSecurityRequirements() {
        return this.securityRequirements;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedOperation setOldOperation(Operation oldOperation) {
        this.oldOperation = oldOperation;
        return this;
    }

    public ChangedOperation setNewOperation(Operation newOperation) {
        this.newOperation = newOperation;
        return this;
    }

    public ChangedOperation setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
        return this;
    }

    public ChangedOperation setHttpMethod(PathItem.HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public ChangedOperation setSummary(ChangedMetadata summary) {
        this.summary = summary;
        return this;
    }

    public ChangedOperation setDescription(ChangedMetadata description) {
        this.description = description;
        return this;
    }

    public ChangedOperation setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public ChangedOperation setParameters(ChangedParameters parameters) {
        this.parameters = parameters;
        return this;
    }

    public ChangedOperation setRequestBody(ChangedRequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public ChangedOperation setApiResponses(ChangedApiResponse apiResponses) {
        this.apiResponses = apiResponses;
        return this;
    }

    public ChangedOperation setSecurityRequirements(ChangedSecurityRequirements securityRequirements) {
        this.securityRequirements = securityRequirements;
        return this;
    }

    public ChangedOperation setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
