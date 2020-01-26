package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.media.Schema;

import java.util.Collections;
import java.util.List;

public class ChangedMediaType implements ComposedChanged {
  private final Schema oldSchema;
  private final Schema newSchema;
  private final DiffContext context;
  private ChangedSchema schema;

  public ChangedMediaType(Schema oldSchema, Schema newSchema, DiffContext context) {
    this.oldSchema = oldSchema;
    this.newSchema = newSchema;
    this.context = context;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Collections.singletonList(schema);
  }

  @Override
  public DiffResult isCoreChanged() {
    return DiffResult.NO_CHANGES;
  }

    public Schema getOldSchema() {
        return this.oldSchema;
    }

    public Schema getNewSchema() {
        return this.newSchema;
    }

    public DiffContext getContext() {
        return this.context;
    }

    public ChangedSchema getSchema() {
        return this.schema;
    }

    public ChangedMediaType setSchema(ChangedSchema schema) {
        this.schema = schema;
        return this;
    }
}
