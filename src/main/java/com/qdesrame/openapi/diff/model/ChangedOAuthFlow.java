package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.security.OAuthFlow;

import java.util.Collections;
import java.util.List;

/** Created by adarsh.sharma on 12/01/18. */
public class ChangedOAuthFlow implements ComposedChanged {
  private OAuthFlow oldOAuthFlow;
  private OAuthFlow newOAuthFlow;

  private boolean authorizationUrl;
  private boolean tokenUrl;
  private boolean refreshUrl;
  private ChangedExtensions extensions;

  public ChangedOAuthFlow(OAuthFlow oldOAuthFlow, OAuthFlow newOAuthFlow) {
    this.oldOAuthFlow = oldOAuthFlow;
    this.newOAuthFlow = newOAuthFlow;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Collections.singletonList(extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    if (authorizationUrl || tokenUrl || refreshUrl) {
      return DiffResult.INCOMPATIBLE;
    }
    return DiffResult.NO_CHANGES;
  }

    public OAuthFlow getOldOAuthFlow() {
        return this.oldOAuthFlow;
    }

    public OAuthFlow getNewOAuthFlow() {
        return this.newOAuthFlow;
    }

    public boolean isAuthorizationUrl() {
        return this.authorizationUrl;
    }

    public boolean isTokenUrl() {
        return this.tokenUrl;
    }

    public boolean isRefreshUrl() {
        return this.refreshUrl;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedOAuthFlow setOldOAuthFlow(OAuthFlow oldOAuthFlow) {
        this.oldOAuthFlow = oldOAuthFlow;
        return this;
    }

    public ChangedOAuthFlow setNewOAuthFlow(OAuthFlow newOAuthFlow) {
        this.newOAuthFlow = newOAuthFlow;
        return this;
    }

    public ChangedOAuthFlow setAuthorizationUrl(boolean authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
        return this;
    }

    public ChangedOAuthFlow setTokenUrl(boolean tokenUrl) {
        this.tokenUrl = tokenUrl;
        return this;
    }

    public ChangedOAuthFlow setRefreshUrl(boolean refreshUrl) {
        this.refreshUrl = refreshUrl;
        return this;
    }

    public ChangedOAuthFlow setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
