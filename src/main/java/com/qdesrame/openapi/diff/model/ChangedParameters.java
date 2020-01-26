package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ChangedParameters implements ComposedChanged {
  private final List<Parameter> oldParameterList;
  private final List<Parameter> newParameterList;
  private final DiffContext context;

  private List<Parameter> increased;
  private List<Parameter> missing;
  private List<ChangedParameter> changed;

  public ChangedParameters(
      List<Parameter> oldParameterList, List<Parameter> newParameterList, DiffContext context) {
    this.oldParameterList = oldParameterList;
    this.newParameterList = newParameterList;
    this.context = context;
    this.increased = new ArrayList<>();
    this.missing = new ArrayList<>();
    this.changed = new ArrayList<>();
  }

  @Override
  public List<Changed> getChangedElements() {
    return new ArrayList<>(changed);
  }

  @Override
  public DiffResult isCoreChanged() {
    if (increased.isEmpty() && missing.isEmpty()) {
      return DiffResult.NO_CHANGES;
    }
    if (increased.stream().noneMatch(Parameter::getRequired) && missing.isEmpty()) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public List<Parameter> getOldParameterList() {
        return this.oldParameterList;
    }

    public List<Parameter> getNewParameterList() {
        return this.newParameterList;
    }

    public DiffContext getContext() {
        return this.context;
    }

    public List<Parameter> getIncreased() {
        return this.increased;
    }

    public List<Parameter> getMissing() {
        return this.missing;
    }

    public List<ChangedParameter> getChanged() {
        return this.changed;
    }

    public ChangedParameters setIncreased(List<Parameter> increased) {
        this.increased = increased;
        return this;
    }

    public ChangedParameters setMissing(List<Parameter> missing) {
        this.missing = missing;
        return this;
    }

    public ChangedParameters setChanged(List<ChangedParameter> changed) {
        this.changed = changed;
        return this;
    }
}
