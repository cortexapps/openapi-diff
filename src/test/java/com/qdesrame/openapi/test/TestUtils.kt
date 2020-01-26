package com.qdesrame.openapi.test

import com.qdesrame.openapi.diff.OpenApiCompare
import org.junit.jupiter.api.Assertions
import org.slf4j.LoggerFactory

object TestUtils {
    private val LOG = LoggerFactory.getLogger(TestUtils::class.java)
    
    @JvmStatic
    fun assertOpenApiAreEquals(oldSpec: String, newSpec: String) {
        val changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec)
        LOG.info("Result: {}", changedOpenApi.isChanged.value)
        Assertions.assertTrue(changedOpenApi.newEndpoints.isEmpty())
        Assertions.assertTrue(changedOpenApi.missingEndpoints.isEmpty())
        Assertions.assertTrue(changedOpenApi.changedOperations.isEmpty())
    }

    @JvmStatic
    fun assertOpenApiChangedEndpoints(oldSpec: String, newSpec: String) {
        val changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec)
        LOG.info("Result: {}", changedOpenApi.isChanged.value)
        org.assertj.core.api.Assertions.assertThat(changedOpenApi.newEndpoints).isEmpty()
        org.assertj.core.api.Assertions.assertThat(changedOpenApi.missingEndpoints).isEmpty()
        org.assertj.core.api.Assertions.assertThat(changedOpenApi.changedOperations).isNotEmpty
    }

    @JvmStatic
    fun assertOpenApiBackwardCompatible(oldSpec: String, newSpec: String, isDiff: Boolean) {
        val changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec)
        LOG.info("Result: {}", changedOpenApi.isChanged.value)
        org.assertj.core.api.Assertions.assertThat(changedOpenApi.isCompatible).isTrue()
    }

    fun assertOpenApiBackwardCompatibleFromContent(oldSpec: String, newSpec: String) {
        val changedOpenApi = OpenApiCompare.fromContents(oldSpec, newSpec)
        LOG.info("Result: {}", changedOpenApi.isChanged.value)
        org.assertj.core.api.Assertions.assertThat(changedOpenApi.isCompatible).isTrue()
    }

    @JvmStatic
    fun assertOpenApiBackwardIncompatible(oldSpec: String, newSpec: String) {
        val changedOpenApi = OpenApiCompare.fromLocations(oldSpec, newSpec)
        LOG.info("Result: {}", changedOpenApi.isChanged.value)
        org.assertj.core.api.Assertions.assertThat(changedOpenApi.isIncompatible).isTrue()
    }

    fun assertOpenApiBackwardIncompatibleFromContent(oldSpec: String, newSpec: String) {
        val changedOpenApi = OpenApiCompare.fromContents(oldSpec, newSpec)
        LOG.info("Result: {}", changedOpenApi.isChanged.value)
        org.assertj.core.api.Assertions.assertThat(changedOpenApi.isIncompatible).isTrue()
    }
}