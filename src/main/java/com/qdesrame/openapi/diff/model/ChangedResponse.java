package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.responses.ApiResponse;

import java.util.Arrays;
import java.util.List;

public class ChangedResponse implements ComposedChanged {
  private final ApiResponse oldApiResponse;
  private final ApiResponse newApiResponse;
  private final DiffContext context;

  private ChangedMetadata description;
  private ChangedHeaders headers;
  private ChangedContent content;
  private ChangedExtensions extensions;

  public ChangedResponse(
      ApiResponse oldApiResponse, ApiResponse newApiResponse, DiffContext context) {
    this.oldApiResponse = oldApiResponse;
    this.newApiResponse = newApiResponse;
    this.context = context;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Arrays.asList(description, headers, content, extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    return DiffResult.NO_CHANGES;
  }

    public ApiResponse getOldApiResponse() {
        return this.oldApiResponse;
    }

    public ApiResponse getNewApiResponse() {
        return this.newApiResponse;
    }

    public DiffContext getContext() {
        return this.context;
    }

    public ChangedMetadata getDescription() {
        return this.description;
    }

    public ChangedHeaders getHeaders() {
        return this.headers;
    }

    public ChangedContent getContent() {
        return this.content;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedResponse setDescription(ChangedMetadata description) {
        this.description = description;
        return this;
    }

    public ChangedResponse setHeaders(ChangedHeaders headers) {
        this.headers = headers;
        return this;
    }

    public ChangedResponse setContent(ChangedContent content) {
        this.content = content;
        return this;
    }

    public ChangedResponse setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
