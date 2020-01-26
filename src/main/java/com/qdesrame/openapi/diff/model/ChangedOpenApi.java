package com.qdesrame.openapi.diff.model;

import com.qdesrame.openapi.diff.utils.EndpointUtils;
import io.swagger.v3.oas.models.OpenAPI;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Created by adarsh.sharma on 22/12/17. */
public class ChangedOpenApi implements ComposedChanged {
  private OpenAPI oldSpecOpenApi;
  private OpenAPI newSpecOpenApi;

  private List<Endpoint> newEndpoints;
  private List<Endpoint> missingEndpoints;
  private List<ChangedOperation> changedOperations;
  private ChangedExtensions changedExtensions;

  public List<Endpoint> getDeprecatedEndpoints() {
    return changedOperations
        .stream()
        .filter(ChangedOperation::isDeprecated)
        .map(
            c ->
                EndpointUtils.convert2Endpoint(
                    c.getPathUrl(), c.getHttpMethod(), c.getNewOperation()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Changed> getChangedElements() {
    return Stream.concat(changedOperations.stream(), Stream.of(changedExtensions))
        .collect(Collectors.toList());
  }

  @Override
  public DiffResult isCoreChanged() {
    if (newEndpoints.isEmpty() && missingEndpoints.isEmpty()) {
      return DiffResult.NO_CHANGES;
    }
    if (missingEndpoints.isEmpty()) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public OpenAPI getOldSpecOpenApi() {
        return this.oldSpecOpenApi;
    }

    public OpenAPI getNewSpecOpenApi() {
        return this.newSpecOpenApi;
    }

    public List<Endpoint> getNewEndpoints() {
        return this.newEndpoints;
    }

    public List<Endpoint> getMissingEndpoints() {
        return this.missingEndpoints;
    }

    public List<ChangedOperation> getChangedOperations() {
        return this.changedOperations;
    }

    public ChangedExtensions getChangedExtensions() {
        return this.changedExtensions;
    }

    public ChangedOpenApi setOldSpecOpenApi(OpenAPI oldSpecOpenApi) {
        this.oldSpecOpenApi = oldSpecOpenApi;
        return this;
    }

    public ChangedOpenApi setNewSpecOpenApi(OpenAPI newSpecOpenApi) {
        this.newSpecOpenApi = newSpecOpenApi;
        return this;
    }

    public ChangedOpenApi setNewEndpoints(List<Endpoint> newEndpoints) {
        this.newEndpoints = newEndpoints;
        return this;
    }

    public ChangedOpenApi setMissingEndpoints(List<Endpoint> missingEndpoints) {
        this.missingEndpoints = missingEndpoints;
        return this;
    }

    public ChangedOpenApi setChangedOperations(List<ChangedOperation> changedOperations) {
        this.changedOperations = changedOperations;
        return this;
    }

    public ChangedOpenApi setChangedExtensions(ChangedExtensions changedExtensions) {
        this.changedExtensions = changedExtensions;
        return this;
    }
}
