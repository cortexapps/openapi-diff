package com.qdesrame.openapi.diff.compare

import com.qdesrame.openapi.diff.model.ChangedRequestBody
import com.qdesrame.openapi.diff.model.DiffContext
import com.qdesrame.openapi.diff.utils.ChangedUtils
import com.qdesrame.openapi.diff.utils.RefPointer
import com.qdesrame.openapi.diff.utils.RefType
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.parameters.RequestBody
import java.util.HashSet
import java.util.Optional

/** Created by adarsh.sharma on 28/12/17.  */
class RequestBodyDiff(private val openApiDiff: OpenApiDiff) : ReferenceDiffCache<RequestBody, ChangedRequestBody>() {
    fun diff(
        left: RequestBody?,
        right: RequestBody?,
        context: DiffContext?
    ): Optional<ChangedRequestBody?> {
        val leftRef = left?.`$ref`
        val rightRef = right?.`$ref`
        return cachedDiff(HashSet(), left, right, leftRef, rightRef, context)
    }

    override fun computeDiff(refSet: HashSet<String>, left: RequestBody?, right: RequestBody?, context: DiffContext): Optional<ChangedRequestBody> {
        var oldRequestContent = Content()
        var newRequestContent = Content()
        var oldRequestBody: RequestBody? = null
        var newRequestBody: RequestBody? = null

        if (left != null) {
            oldRequestBody = refPointer.resolveRef(openApiDiff.oldSpecOpenApi.components, left, left.`$ref`)

            if (oldRequestBody.content != null) {
                oldRequestContent = oldRequestBody.content
            }
        }

        if (right != null) {
            newRequestBody = refPointer.resolveRef(openApiDiff.newSpecOpenApi.components, right, right.`$ref`)
            if (newRequestBody.content != null) {
                newRequestContent = newRequestBody.content
            }
        }

        val leftRequired = oldRequestBody?.required ?: false
        val rightRequired = newRequestBody?.required ?: false
        val changedRequestBody = ChangedRequestBody(oldRequestBody, newRequestBody, context)
                .setChangeRequired(leftRequired != rightRequired)
        openApiDiff
                .metadataDiff
                .diff(oldRequestBody?.description, newRequestBody?.description, context)
                .ifPresent { changedRequestBody.description = it }
        openApiDiff
                .contentDiff
                .diff(oldRequestContent, newRequestContent, context)
                .ifPresent { changedRequestBody.content = it }
        openApiDiff
                .extensionsDiff
                .diff(getExtensions(left), getExtensions(right), context)
                .ifPresent { changedRequestBody.extensions = it }
        return ChangedUtils.isChanged(changedRequestBody)
    }

    companion object {
        private val refPointer = RefPointer<RequestBody>(RefType.REQUEST_BODIES)
        private fun getExtensions(body: RequestBody?): Map<String, Any>? = body?.extensions
    }
}