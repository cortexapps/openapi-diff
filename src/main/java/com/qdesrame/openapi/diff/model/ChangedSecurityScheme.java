package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Arrays;
import java.util.List;

/** Created by adarsh.sharma on 11/01/18. */
public class ChangedSecurityScheme implements ComposedChanged {
  private SecurityScheme oldSecurityScheme;
  private SecurityScheme newSecurityScheme;

  private boolean changedType;
  private boolean changedIn;
  private boolean changedScheme;
  private boolean changedBearerFormat;
  private boolean changedOpenIdConnectUrl;
  private ChangedSecuritySchemeScopes changedScopes;
  private ChangedMetadata description;
  private ChangedOAuthFlows oAuthFlows;
  private ChangedExtensions extensions;

  public ChangedSecurityScheme(SecurityScheme oldSecurityScheme, SecurityScheme newSecurityScheme) {
    this.oldSecurityScheme = oldSecurityScheme;
    this.newSecurityScheme = newSecurityScheme;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Arrays.asList(description, oAuthFlows, extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    if (!changedType
        && !changedIn
        && !changedScheme
        && !changedBearerFormat
        && !changedOpenIdConnectUrl
        && (changedScopes == null || changedScopes.isUnchanged())) {
      return DiffResult.NO_CHANGES;
    }
    if (!changedType
        && !changedIn
        && !changedScheme
        && !changedBearerFormat
        && !changedOpenIdConnectUrl
        && (changedScopes == null || changedScopes.getIncreased().isEmpty())) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }

    public SecurityScheme getOldSecurityScheme() {
        return this.oldSecurityScheme;
    }

    public SecurityScheme getNewSecurityScheme() {
        return this.newSecurityScheme;
    }

    public boolean isChangedType() {
        return this.changedType;
    }

    public boolean isChangedIn() {
        return this.changedIn;
    }

    public boolean isChangedScheme() {
        return this.changedScheme;
    }

    public boolean isChangedBearerFormat() {
        return this.changedBearerFormat;
    }

    public boolean isChangedOpenIdConnectUrl() {
        return this.changedOpenIdConnectUrl;
    }

    public ChangedSecuritySchemeScopes getChangedScopes() {
        return this.changedScopes;
    }

    public ChangedMetadata getDescription() {
        return this.description;
    }

    public ChangedOAuthFlows getOAuthFlows() {
        return this.oAuthFlows;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedSecurityScheme setOldSecurityScheme(SecurityScheme oldSecurityScheme) {
        this.oldSecurityScheme = oldSecurityScheme;
        return this;
    }

    public ChangedSecurityScheme setNewSecurityScheme(SecurityScheme newSecurityScheme) {
        this.newSecurityScheme = newSecurityScheme;
        return this;
    }

    public ChangedSecurityScheme setChangedType(boolean changedType) {
        this.changedType = changedType;
        return this;
    }

    public ChangedSecurityScheme setChangedIn(boolean changedIn) {
        this.changedIn = changedIn;
        return this;
    }

    public ChangedSecurityScheme setChangedScheme(boolean changedScheme) {
        this.changedScheme = changedScheme;
        return this;
    }

    public ChangedSecurityScheme setChangedBearerFormat(boolean changedBearerFormat) {
        this.changedBearerFormat = changedBearerFormat;
        return this;
    }

    public ChangedSecurityScheme setChangedOpenIdConnectUrl(boolean changedOpenIdConnectUrl) {
        this.changedOpenIdConnectUrl = changedOpenIdConnectUrl;
        return this;
    }

    public ChangedSecurityScheme setChangedScopes(ChangedSecuritySchemeScopes changedScopes) {
        this.changedScopes = changedScopes;
        return this;
    }

    public ChangedSecurityScheme setDescription(ChangedMetadata description) {
        this.description = description;
        return this;
    }

    public ChangedSecurityScheme setOAuthFlows(ChangedOAuthFlows oAuthFlows) {
        this.oAuthFlows = oAuthFlows;
        return this;
    }

    public ChangedSecurityScheme setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
