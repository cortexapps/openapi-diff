package com.qdesrame.openapi.diff.compare

import com.qdesrame.openapi.test.TestUtils.assertOpenApiBackwardCompatibleFromContent
import com.qdesrame.openapi.test.TestUtils.assertOpenApiBackwardIncompatibleFromContent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ApiResponseDiffTest : Assertions() {
    /***
     * This is a regression test - when no responses were set, we would get an exception
     * since the OpenAPI object has a `null` ApiResponses field.
     */
    @Test
    fun `no response in previous`() {
        val old = """
            openapi: 3.0.0
            info:
              title: Swagger Petstore
            paths:
              /store/inventory:
                get:
                    operationId: asdf
                    
        """.trimIndent()

        val new = """
            openapi: 3.0.0
            info:
              title: Swagger Petstore
            paths:
              /store/inventory:
                get:
                  responses:
                    '200':
                      content:
                        application/json:
                          schema:
                            type: object
                            properties:
                                title:
                                    type: string

        """.trimIndent()

        // The previous API had no response, so adding a response shape is still compatible.
        assertOpenApiBackwardCompatibleFromContent(old, new)

        // Removing the response shape is backwards incompatible.
        assertOpenApiBackwardIncompatibleFromContent(new, old)
    }
}