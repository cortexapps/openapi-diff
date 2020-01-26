package com.qdesrame.openapi.diff.model;

import io.swagger.v3.oas.models.security.OAuthFlows;

import java.util.Arrays;
import java.util.List;

/** Created by adarsh.sharma on 12/01/18. */
public class ChangedOAuthFlows implements ComposedChanged {
  private final OAuthFlows oldOAuthFlows;
  private final OAuthFlows newOAuthFlows;

  private ChangedOAuthFlow implicitOAuthFlow;
  private ChangedOAuthFlow passwordOAuthFlow;
  private ChangedOAuthFlow clientCredentialOAuthFlow;
  private ChangedOAuthFlow authorizationCodeOAuthFlow;
  private ChangedExtensions extensions;

  public ChangedOAuthFlows(OAuthFlows oldOAuthFlows, OAuthFlows newOAuthFlows) {
    this.oldOAuthFlows = oldOAuthFlows;
    this.newOAuthFlows = newOAuthFlows;
  }

  @Override
  public List<Changed> getChangedElements() {
    return Arrays.asList(
        implicitOAuthFlow,
        passwordOAuthFlow,
        clientCredentialOAuthFlow,
        authorizationCodeOAuthFlow,
        extensions);
  }

  @Override
  public DiffResult isCoreChanged() {
    return DiffResult.NO_CHANGES;
  }

    public OAuthFlows getOldOAuthFlows() {
        return this.oldOAuthFlows;
    }

    public OAuthFlows getNewOAuthFlows() {
        return this.newOAuthFlows;
    }

    public ChangedOAuthFlow getImplicitOAuthFlow() {
        return this.implicitOAuthFlow;
    }

    public ChangedOAuthFlow getPasswordOAuthFlow() {
        return this.passwordOAuthFlow;
    }

    public ChangedOAuthFlow getClientCredentialOAuthFlow() {
        return this.clientCredentialOAuthFlow;
    }

    public ChangedOAuthFlow getAuthorizationCodeOAuthFlow() {
        return this.authorizationCodeOAuthFlow;
    }

    public ChangedExtensions getExtensions() {
        return this.extensions;
    }

    public ChangedOAuthFlows setImplicitOAuthFlow(ChangedOAuthFlow implicitOAuthFlow) {
        this.implicitOAuthFlow = implicitOAuthFlow;
        return this;
    }

    public ChangedOAuthFlows setPasswordOAuthFlow(ChangedOAuthFlow passwordOAuthFlow) {
        this.passwordOAuthFlow = passwordOAuthFlow;
        return this;
    }

    public ChangedOAuthFlows setClientCredentialOAuthFlow(ChangedOAuthFlow clientCredentialOAuthFlow) {
        this.clientCredentialOAuthFlow = clientCredentialOAuthFlow;
        return this;
    }

    public ChangedOAuthFlows setAuthorizationCodeOAuthFlow(ChangedOAuthFlow authorizationCodeOAuthFlow) {
        this.authorizationCodeOAuthFlow = authorizationCodeOAuthFlow;
        return this;
    }

    public ChangedOAuthFlows setExtensions(ChangedExtensions extensions) {
        this.extensions = extensions;
        return this;
    }
}
