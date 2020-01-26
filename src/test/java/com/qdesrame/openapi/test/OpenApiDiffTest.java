package com.qdesrame.openapi.test;

import com.qdesrame.openapi.diff.OpenApiCompare;
import com.qdesrame.openapi.diff.model.ChangedOpenApi;
import com.qdesrame.openapi.diff.model.ChangedOperation;
import com.qdesrame.openapi.diff.model.Endpoint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.qdesrame.openapi.test.TestUtils.assertOpenApiAreEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class OpenApiDiffTest {

  final String SWAGGER_V2_HTTP = "http://petstore.swagger.io/v2/swagger.json";
  private final String OPENAPI_DOC1 = "petstore_v2_1.yaml";
  private final String OPENAPI_DOC2 = "petstore_v2_2.yaml";
  private final String OPENAPI_EMPTY_DOC = "petstore_v2_empty.yaml";

  @Test
  public void testEqual() {
    assertOpenApiAreEquals(OPENAPI_DOC2, OPENAPI_DOC2);
  }

  @Test
  public void testNewApi() {
    ChangedOpenApi changedOpenApi = OpenApiCompare.fromLocations(OPENAPI_EMPTY_DOC, OPENAPI_DOC2);
    List<Endpoint> newEndpoints = changedOpenApi.getNewEndpoints();
    List<Endpoint> missingEndpoints = changedOpenApi.getMissingEndpoints();
    List<ChangedOperation> changedEndPoints = changedOpenApi.getChangedOperations();
    assertThat(newEndpoints).isNotEmpty();
    assertThat(missingEndpoints).isEmpty();
    assertThat(changedEndPoints).isEmpty();
  }

  @Test
  public void testDeprecatedApi() {
    ChangedOpenApi changedOpenApi = OpenApiCompare.fromLocations(OPENAPI_DOC1, OPENAPI_EMPTY_DOC);
    List<Endpoint> newEndpoints = changedOpenApi.getNewEndpoints();
    List<Endpoint> missingEndpoints = changedOpenApi.getMissingEndpoints();
    List<ChangedOperation> changedEndPoints = changedOpenApi.getChangedOperations();
    assertThat(newEndpoints).isEmpty();
    assertThat(missingEndpoints).isNotEmpty();
    assertThat(changedEndPoints).isEmpty();
  }

  @Test
  public void testDiff() {
    ChangedOpenApi changedOpenApi = OpenApiCompare.fromLocations(OPENAPI_DOC1, OPENAPI_DOC2);
    List<ChangedOperation> changedEndPoints = changedOpenApi.getChangedOperations();
    assertThat(changedEndPoints).isNotEmpty();
  }

}
