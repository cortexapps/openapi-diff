package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Created by adarsh.sharma on 22/12/17. */
public class ChangedApiResponse implements ComposedChanged {
  private final ApiResponses oldApiResponses;
  private final ApiResponses newApiResponses;
  private final DiffContext context;

  private Map<String, ApiResponse> increased;
  private Map<String, ApiResponse> missing;
  private Map<String, ChangedResponse> changed;
  private ChangedExtensions extensions;

  public ChangedApiResponse(
      ApiResponses oldApiResponses, ApiResponses newApiResponses, DiffContext context) {
    this.oldApiResponses = oldApiResponses;
    this.newApiResponses = newApiResponses;
    this.context = context;
    this.missing = new LinkedHashMap<>();
    this.increased = new LinkedHashMap<>();
    this.changed = new LinkedHashMap<>();
  }

  @Override
  public List<Changed> getChangedElements() {
    return Stream.concat(changed.values().stream(), Stream.of(extensions))
        .collect(Collectors.toList());
  }

  @Override
  public DiffResult isCoreChanged() {
    if (increased.isEmpty() && missing.isEmpty()) {
      return DiffResult.NO_CHANGES;
    }
    if (!increased.isEmpty() && missing.isEmpty()) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public ApiResponses getOldApiResponses() {
        return this.oldApiResponses;
    }

    public ApiResponses getNewApiResponses() {
        return this.newApiResponses;
    }

    public DiffContext getContext() {
        return this.context;
    }

    public Map<String, ApiResponse> getIncreased() {
        return this.increased;
    }

    public Map<String, ApiResponse> getMissing() {
        return this.missing;
    }

    public Map<String, ChangedResponse> getChanged() {
        return this.changed;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedApiResponse setIncreased(Map<String, ApiResponse> increased) {
        this.increased = increased;
        return this;
    }

    public ChangedApiResponse setMissing(Map<String, ApiResponse> missing) {
        this.missing = missing;
        return this;
    }

    public ChangedApiResponse setChanged(Map<String, ChangedResponse> changed) {
        this.changed = changed;
        return this;
    }

    public ChangedApiResponse setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
