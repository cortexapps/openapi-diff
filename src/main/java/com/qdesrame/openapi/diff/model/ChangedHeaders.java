package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.headers.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** Created by adarsh.sharma on 28/12/17. */
public class ChangedHeaders implements ComposedChanged {
  private final Map<String, Header> oldHeaders;
  private final Map<String, Header> newHeaders;
  private final DiffContext context;

  private Map<String, Header> increased;
  private Map<String, Header> missing;
  private Map<String, ChangedHeader> changed;

  public ChangedHeaders(
      Map<String, Header> oldHeaders, Map<String, Header> newHeaders, DiffContext context) {
    this.oldHeaders = oldHeaders;
    this.newHeaders = newHeaders;
    this.context = context;
  }

  @Override
  public List<Changed> getChangedElements() {
    return new ArrayList<>(changed.values());
  }

  @Override
  public DiffResult isCoreChanged() {
    if (increased.isEmpty() && missing.isEmpty()) {
      return DiffResult.NO_CHANGES;
    }
    if (missing.isEmpty()) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public Map<String, Header> getOldHeaders() {
        return this.oldHeaders;
    }

    public Map<String, Header> getNewHeaders() {
        return this.newHeaders;
    }

    public DiffContext getContext() {
        return this.context;
    }

    public Map<String, Header> getIncreased() {
        return this.increased;
    }

    public Map<String, Header> getMissing() {
        return this.missing;
    }

    public Map<String, ChangedHeader> getChanged() {
        return this.changed;
    }

    public ChangedHeaders setIncreased(Map<String, Header> increased) {
        this.increased = increased;
        return this;
    }

    public ChangedHeaders setMissing(Map<String, Header> missing) {
        this.missing = missing;
        return this;
    }

    public ChangedHeaders setChanged(Map<String, ChangedHeader> changed) {
        this.changed = changed;
        return this;
    }
}
