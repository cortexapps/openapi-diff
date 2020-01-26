package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.parameters.RequestBody;

import java.util.Arrays;
import java.util.List;

/** Created by adarsh.sharma on 27/12/17. */
public class ChangedRequestBody implements ComposedChanged {
  private final RequestBody oldRequestBody;
  private final RequestBody newRequestBody;
  private final DiffContext context;

  private boolean changeRequired;
  private ChangedMetadata description;
  private ChangedContent content;
  private ChangedExtensions extensions;

  public ChangedRequestBody(
      RequestBody oldRequestBody, RequestBody newRequestBody, DiffContext context) {
    this.oldRequestBody = oldRequestBody;
    this.newRequestBody = newRequestBody;
    this.context = context;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Arrays.asList(description, content, extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    if (!changeRequired) {
      return DiffResult.NO_CHANGES;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public RequestBody getOldRequestBody() {
        return this.oldRequestBody;
    }

    public RequestBody getNewRequestBody() {
        return this.newRequestBody;
    }

    public DiffContext getContext() {
        return this.context;
    }

    public boolean isChangeRequired() {
        return this.changeRequired;
    }

    public ChangedMetadata getDescription() {
        return this.description;
    }

    public ChangedContent getContent() {
        return this.content;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedRequestBody setChangeRequired(boolean changeRequired) {
        this.changeRequired = changeRequired;
        return this;
    }

    public ChangedRequestBody setDescription(ChangedMetadata description) {
        this.description = description;
        return this;
    }

    public ChangedRequestBody setContent(ChangedContent content) {
        this.content = content;
        return this;
    }

    public ChangedRequestBody setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
