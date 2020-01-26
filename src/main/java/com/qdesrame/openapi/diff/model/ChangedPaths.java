package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.PathItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChangedPaths implements ComposedChanged {
  private final Map<String, PathItem> oldPathMap;
  private final Map<String, PathItem> newPathMap;

  private Map<String, PathItem> increased;
  private Map<String, PathItem> missing;
  private Map<String, ChangedPath> changed;

  public ChangedPaths(Map<String, PathItem> oldPathMap, Map<String, PathItem> newPathMap) {
    this.oldPathMap = oldPathMap;
    this.newPathMap = newPathMap;
    this.increased = new LinkedHashMap<>();
    this.missing = new LinkedHashMap<>();
    this.changed = new LinkedHashMap<>();
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

    public Map<String, PathItem> getOldPathMap() {
        return this.oldPathMap;
    }

    public Map<String, PathItem> getNewPathMap() {
        return this.newPathMap;
    }

    public Map<String, PathItem> getIncreased() {
        return this.increased;
    }

    public Map<String, PathItem> getMissing() {
        return this.missing;
    }

    public Map<String, ChangedPath> getChanged() {
        return this.changed;
    }

    public void setIncreased(Map<String, PathItem> increased) {
        this.increased = increased;
    }

    public void setMissing(Map<String, PathItem> missing) {
        this.missing = missing;
    }

    public void setChanged(Map<String, ChangedPath> changed) {
        this.changed = changed;
    }
}
