package com.qdesrame.openapi.test;

import com.qdesrame.openapi.diff.OpenApiCompare;
import com.qdesrame.openapi.diff.model.ChangedOpenApi;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

public class TestUtils {
  public static final Logger LOG = getLogger(TestUtils.class);

  public static void assertOpenApiAreEquals(String oldSpec, String newSpec) {
    ChangedOpenApi changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec);
    LOG.info("Result: {}", changedOpenApi.isChanged().getValue());
    assertTrue(changedOpenApi.getNewEndpoints().isEmpty());
    assertTrue(changedOpenApi.getMissingEndpoints().isEmpty());
    assertTrue(changedOpenApi.getChangedOperations().isEmpty());
  }

  public static void assertOpenApiChangedEndpoints(String oldSpec, String newSpec) {
    ChangedOpenApi changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec);
    LOG.info("Result: {}", changedOpenApi.isChanged().getValue());
    assertThat(changedOpenApi.getNewEndpoints()).isEmpty();
    assertThat(changedOpenApi.getMissingEndpoints()).isEmpty();
    assertThat(changedOpenApi.getChangedOperations()).isNotEmpty();
  }

  public static void assertOpenApiBackwardCompatible(
      String oldSpec, String newSpec, boolean isDiff) {
    ChangedOpenApi changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec);
    LOG.info("Result: {}", changedOpenApi.isChanged().getValue());
    assertThat(changedOpenApi.isCompatible()).isTrue();
  }

  public static void assertOpenApiBackwardIncompatible(String oldSpec, String newSpec) {
    ChangedOpenApi changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec);
    LOG.info("Result: {}", changedOpenApi.isChanged().getValue());
    assertThat(changedOpenApi.isIncompatible()).isTrue();
  }
}
