package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.headers.Header;

import java.util.Arrays;
import java.util.List;

/** Created by adarsh.sharma on 28/12/17. */
public class ChangedHeader implements ComposedChanged {
  private final Header oldHeader;
  private final Header newHeader;
  private final DiffContext context;

  private boolean required;
  private boolean deprecated;
  private boolean style;
  private boolean explode;
  private ChangedMetadata description;
  private ChangedSchema schema;
  private ChangedContent content;
  private ChangedExtensions extensions;

  public ChangedHeader(Header oldHeader, Header newHeader, DiffContext context) {
    this.oldHeader = oldHeader;
    this.newHeader = newHeader;
    this.context = context;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Arrays.asList(description, schema, content, extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    if (!required && !deprecated && !style && !explode) {
      return DiffResult.NO_CHANGES;
    }
    if (!required && !style && !explode) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public Header getOldHeader() {
        return this.oldHeader;
    }

    public Header getNewHeader() {
        return this.newHeader;
    }

    public DiffContext getContext() {
        return this.context;
    }

    public boolean isRequired() {
        return this.required;
    }

    public boolean isDeprecated() {
        return this.deprecated;
    }

    public boolean isStyle() {
        return this.style;
    }

    public boolean isExplode() {
        return this.explode;
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

    public ChangedHeader setRequired(boolean required) {
        this.required = required;
        return this;
    }

    public ChangedHeader setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    public ChangedHeader setStyle(boolean style) {
        this.style = style;
        return this;
    }

    public ChangedHeader setExplode(boolean explode) {
        this.explode = explode;
        return this;
    }

    public ChangedHeader setDescription(ChangedMetadata description) {
        this.description = description;
        return this;
    }

    public ChangedHeader setSchema(ChangedSchema schema) {
        this.schema = schema;
        return this;
    }

    public ChangedHeader setContent(ChangedContent content) {
        this.content = content;
        return this;
    }

    public ChangedHeader setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
