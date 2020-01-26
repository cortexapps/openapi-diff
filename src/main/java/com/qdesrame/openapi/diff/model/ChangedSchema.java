package com.qdesrame.openapi.diff.model;

import com.qdesrame.openapi.diff.model.schema.ChangedEnum;
import com.qdesrame.openapi.diff.model.schema.ChangedMaxLength;
import com.qdesrame.openapi.diff.model.schema.ChangedReadOnly;
import com.qdesrame.openapi.diff.model.schema.ChangedRequired;
import com.qdesrame.openapi.diff.model.schema.ChangedWriteOnly;
import io.swagger.v3.oas.models.media.Schema;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Created by adarsh.sharma on 22/12/17. */
public class ChangedSchema implements ComposedChanged {
  protected DiffContext context;
  protected Schema oldSchema;
  protected Schema newSchema;
  protected String type;
  protected Map<String, ChangedSchema> changedProperties;
  protected Map<String, Schema> increasedProperties;
  protected Map<String, Schema> missingProperties;
  protected boolean changeDeprecated;
  protected ChangedMetadata description;
  protected boolean changeTitle;
  protected ChangedRequired required;
  protected boolean changeDefault;
  protected ChangedEnum<?> enumeration;
  protected boolean changeFormat;
  protected ChangedReadOnly readOnly;
  protected ChangedWriteOnly writeOnly;
  protected boolean changedType;
  protected ChangedMaxLength maxLength;
  protected boolean discriminatorPropertyChanged;
  protected ChangedSchema items;
  protected ChangedOneOfSchema oneOfSchema;
  protected ChangedSchema addProp;
  private ChangedExtensions extensions;

  public ChangedSchema() {
    increasedProperties = new LinkedHashMap<>();
    missingProperties = new LinkedHashMap<>();
    changedProperties = new LinkedHashMap<>();
  }

  @Override
  public List<Changed> getChangedElements() {
    return Stream.concat(
            changedProperties.values().stream(),
            Stream.of(
                description,
                readOnly,
                writeOnly,
                items,
                oneOfSchema,
                addProp,
                enumeration,
                required,
                maxLength,
                extensions))
        .collect(Collectors.toList());
  }

  @Override
  public DiffResult isCoreChanged() {
    if (!changedType
        && (oldSchema == null && newSchema == null || oldSchema != null && newSchema != null)
        && !changeFormat
        && increasedProperties.size() == 0
        && missingProperties.size() == 0
        && changedProperties.values().size() == 0
        && !changeDeprecated
        && !discriminatorPropertyChanged) {
      return DiffResult.NO_CHANGES;
    }
    boolean compatibleForRequest = (oldSchema != null || newSchema == null);
    boolean compatibleForResponse =
        missingProperties.isEmpty() && (oldSchema == null || newSchema != null);
    if ((context.isRequest() && compatibleForRequest
            || context.isResponse() && compatibleForResponse)
        && !changedType
        && !discriminatorPropertyChanged) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public DiffContext getContext() {
        return this.context;
    }

    public Schema getOldSchema() {
        return this.oldSchema;
    }

    public Schema getNewSchema() {
        return this.newSchema;
    }

    public String getType() {
        return this.type;
    }

    public Map<String, ChangedSchema> getChangedProperties() {
        return this.changedProperties;
    }

    public Map<String, Schema> getIncreasedProperties() {
        return this.increasedProperties;
    }

    public Map<String, Schema> getMissingProperties() {
        return this.missingProperties;
    }

    public boolean isChangeDeprecated() {
        return this.changeDeprecated;
    }

    public ChangedMetadata getDescription() {
        return this.description;
    }

    public boolean isChangeTitle() {
        return this.changeTitle;
    }

    public ChangedRequired getRequired() {
        return this.required;
    }

    public boolean isChangeDefault() {
        return this.changeDefault;
    }

    public ChangedEnum<?> getEnumeration() {
        return this.enumeration;
    }

    public boolean isChangeFormat() {
        return this.changeFormat;
    }

    public ChangedReadOnly getReadOnly() {
        return this.readOnly;
    }

    public ChangedWriteOnly getWriteOnly() {
        return this.writeOnly;
    }

    public boolean isChangedType() {
        return this.changedType;
    }

    public ChangedMaxLength getMaxLength() {
        return this.maxLength;
    }

    public boolean isDiscriminatorPropertyChanged() {
        return this.discriminatorPropertyChanged;
    }

    public ChangedSchema getItems() {
        return this.items;
    }

    public ChangedOneOfSchema getOneOfSchema() {
        return this.oneOfSchema;
    }

    public ChangedSchema getAddProp() {
        return this.addProp;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedSchema setContext(DiffContext context) {
        this.context = context;
        return this;
    }

    public ChangedSchema setOldSchema(Schema oldSchema) {
        this.oldSchema = oldSchema;
        return this;
    }

    public ChangedSchema setNewSchema(Schema newSchema) {
        this.newSchema = newSchema;
        return this;
    }

    public ChangedSchema setType(String type) {
        this.type = type;
        return this;
    }

    public ChangedSchema setChangedProperties(Map<String, ChangedSchema> changedProperties) {
        this.changedProperties = changedProperties;
        return this;
    }

    public ChangedSchema setIncreasedProperties(Map<String, Schema> increasedProperties) {
        this.increasedProperties = increasedProperties;
        return this;
    }

    public ChangedSchema setMissingProperties(Map<String, Schema> missingProperties) {
        this.missingProperties = missingProperties;
        return this;
    }

    public ChangedSchema setChangeDeprecated(boolean changeDeprecated) {
        this.changeDeprecated = changeDeprecated;
        return this;
    }

    public ChangedSchema setDescription(ChangedMetadata description) {
        this.description = description;
        return this;
    }

    public ChangedSchema setChangeTitle(boolean changeTitle) {
        this.changeTitle = changeTitle;
        return this;
    }

    public ChangedSchema setRequired(ChangedRequired required) {
        this.required = required;
        return this;
    }

    public ChangedSchema setChangeDefault(boolean changeDefault) {
        this.changeDefault = changeDefault;
        return this;
    }

    public ChangedSchema setEnumeration(ChangedEnum<?> enumeration) {
        this.enumeration = enumeration;
        return this;
    }

    public ChangedSchema setChangeFormat(boolean changeFormat) {
        this.changeFormat = changeFormat;
        return this;
    }

    public ChangedSchema setReadOnly(ChangedReadOnly readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public ChangedSchema setWriteOnly(ChangedWriteOnly writeOnly) {
        this.writeOnly = writeOnly;
        return this;
    }

    public ChangedSchema setChangedType(boolean changedType) {
        this.changedType = changedType;
        return this;
    }

    public ChangedSchema setMaxLength(ChangedMaxLength maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public ChangedSchema setDiscriminatorPropertyChanged(boolean discriminatorPropertyChanged) {
        this.discriminatorPropertyChanged = discriminatorPropertyChanged;
        return this;
    }

    public ChangedSchema setItems(ChangedSchema items) {
        this.items = items;
        return this;
    }

    public ChangedSchema setOneOfSchema(ChangedOneOfSchema oneOfSchema) {
        this.oneOfSchema = oneOfSchema;
        return this;
    }

    public ChangedSchema setAddProp(ChangedSchema addProp) {
        this.addProp = addProp;
        return this;
    }

    public ChangedSchema setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
