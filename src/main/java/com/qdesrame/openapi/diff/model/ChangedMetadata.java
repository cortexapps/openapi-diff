package com.qdesrame.openapi.diff.model;

import java.util.Objects;

public class ChangedMetadata implements Changed {

  private String left;
  private String right;

  @Override
  public DiffResult isChanged() {
    if (Objects.equals(left, right)) {
      return DiffResult.NO_CHANGES;
    }
    return DiffResult.METADATA;
  }

    public String getLeft() {
        return this.left;
    }

    public String getRight() {
        return this.right;
    }

    public ChangedMetadata setLeft(String left) {
        this.left = left;
        return this;
    }

    public ChangedMetadata setRight(String right) {
        this.right = right;
        return this;
    }
}
