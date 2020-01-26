package com.qdesrame.openapi.diff.compare

import com.qdesrame.openapi.diff.model.ChangedExtensions
import com.qdesrame.openapi.diff.model.ChangedOperation
import com.qdesrame.openapi.diff.model.ChangedParameters
import com.qdesrame.openapi.diff.model.ChangedRequestBody
import com.qdesrame.openapi.diff.model.ChangedSecurityRequirements
import com.qdesrame.openapi.diff.model.DiffContext
import com.qdesrame.openapi.diff.utils.ChangedUtils
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.parameters.Parameter
import java.util.Optional

class OperationDiff(private val openApiDiff: OpenApiDiff) {
    fun diff(oldOperation: Operation, newOperation: Operation, context: DiffContext): Optional<ChangedOperation> {
        val changedOperation = ChangedOperation(context.url, context.method, oldOperation, newOperation)
        openApiDiff
            .metadataDiff
            .diff(oldOperation.summary, newOperation.summary, context)
            .ifPresent { changedOperation.summary = it }

        openApiDiff
                .metadataDiff
                .diff(oldOperation.description, newOperation.description, context)
                .ifPresent { changedOperation.description = it }

        changedOperation.isDeprecated = (!(oldOperation.deprecated ?: false) && (newOperation.deprecated ?: false))

        if (oldOperation.requestBody != null || newOperation.requestBody != null) {
            openApiDiff
                    .requestBodyDiff
                    .diff(
                            oldOperation.requestBody, newOperation.requestBody, context.copyAsRequest())
                    .ifPresent { requestBody: ChangedRequestBody -> changedOperation.requestBody = requestBody }
        }
        openApiDiff
                .parametersDiff
                .diff(oldOperation.parameters, newOperation.parameters, context)
                .ifPresent {
                    removePathParameters(context.parameters, it)
                    changedOperation.parameters = it
                }

        if (oldOperation.responses != null || newOperation.responses != null) {
            openApiDiff
                    .apiResponseDiff
                    .diff(oldOperation.responses, newOperation.responses, context.copyAsResponse())
                    .ifPresent { changedOperation.apiResponses = it }
        }

        if (oldOperation.security != null || newOperation.security != null) {
            openApiDiff
                    .securityRequirementsDiff
                    .diff(oldOperation.security, newOperation.security, context)
                    .ifPresent { securityRequirements: ChangedSecurityRequirements? -> changedOperation.securityRequirements = securityRequirements }
        }

        openApiDiff
                .extensionsDiff
                .diff(oldOperation.extensions, newOperation.extensions, context)
                .ifPresent { extensions: ChangedExtensions? -> changedOperation.extensions = extensions }
        return ChangedUtils.isChanged(changedOperation)
    }

    private fun removePathParameters(pathParameters: Map<String, String>, params: ChangedParameters) {
        pathParameters.forEach { (oldParam: String, newParam: String) ->
            removePathParameter(oldParam, params.missing)
            removePathParameter(newParam, params.increased)
        }
    }

    private fun removePathParameter(name: String, parameters: MutableList<Parameter>) {
        parameters
                .firstOrNull { "path" == it.getIn() && name == it.name }
                .let { parameters.remove(it) }
    }
}