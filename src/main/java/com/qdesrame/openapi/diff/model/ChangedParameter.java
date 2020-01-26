package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.parameters.Parameter;

import java.util.Arrays;
import java.util.List;

public class ChangedParameter implements ComposedChanged {
  private final DiffContext context;
  private Parameter oldParameter;
  private Parameter newParameter;
  private String name;
  private String in;
  private boolean changeRequired;
  private boolean deprecated;
  private boolean changeStyle;
  private boolean changeExplode;
  private boolean changeAllowEmptyValue;
  private ChangedMetadata description;
  private ChangedSchema schema;
  private ChangedContent content;
  private ChangedExtensions extensions;

  public ChangedParameter(String name, String in, DiffContext context) {
    this.name = name;
    this.in = in;
    this.context = context;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Arrays.asList(description, schema, content, extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    if (!changeRequired
        && !deprecated
        && !changeAllowEmptyValue
        && !changeStyle
        && !changeExplode) {
      return DiffResult.NO_CHANGES;
    }
    if ((!changeRequired || Boolean.TRUE.equals(oldParameter.getRequired()))
        && (!changeAllowEmptyValue || Boolean.TRUE.equals(newParameter.getAllowEmptyValue()))
        && !changeStyle
        && !changeExplode) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public DiffContext getContext() {
        return this.context;
    }

    public Parameter getOldParameter() {
        return this.oldParameter;
    }

    public Parameter getNewParameter() {
        return this.newParameter;
    }

    public String getName() {
        return this.name;
    }

    public String getIn() {
        return this.in;
    }

    public boolean isChangeRequired() {
        return this.changeRequired;
    }

    public boolean isDeprecated() {
        return this.deprecated;
    }

    public boolean isChangeStyle() {
        return this.changeStyle;
    }

    public boolean isChangeExplode() {
        return this.changeExplode;
    }

    public boolean isChangeAllowEmptyValue() {
        return this.changeAllowEmptyValue;
    }

    public ChangedMetadata getDescription() {
        return this.description;
    }

    public ChangedSchema getSchema() {
        return this.schema;
    }

    public ChangedContent getContent() {
        return this.content;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedParameter setOldParameter(Parameter oldParameter) {
        this.oldParameter = oldParameter;
        return this;
    }

    public ChangedParameter setNewParameter(Parameter newParameter) {
        this.newParameter = newParameter;
        return this;
    }

    public ChangedParameter setName(String name) {
        this.name = name;
        return this;
    }

    public ChangedParameter setIn(String in) {
        this.in = in;
        return this;
    }

    public ChangedParameter setChangeRequired(boolean changeRequired) {
        this.changeRequired = changeRequired;
        return this;
    }

    public ChangedParameter setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public ChangedParameter setChangeStyle(boolean changeStyle) {
        this.changeStyle = changeStyle;
        return this;
    }

    public ChangedParameter setChangeExplode(boolean changeExplode) {
        this.changeExplode = changeExplode;
        return this;
    }

    public ChangedParameter setChangeAllowEmptyValue(boolean changeAllowEmptyValue) {
        this.changeAllowEmptyValue = changeAllowEmptyValue;
        return this;
    }

    public ChangedParameter setDescription(ChangedMetadata description) {
        this.description = description;
        return this;
    }

    public ChangedParameter setSchema(ChangedSchema schema) {
        this.schema = schema;
        return this;
    }

    public ChangedParameter setContent(ChangedContent content) {
        this.content = content;
        return this;
    }

    public ChangedParameter setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
