package com.qdesrame.openapi.diff.compare

import com.qdesrame.openapi.diff.model.ChangedApiResponse
import com.qdesrame.openapi.diff.model.ChangedExtensions
import com.qdesrame.openapi.diff.model.ChangedResponse
import com.qdesrame.openapi.diff.model.DiffContext
import com.qdesrame.openapi.diff.utils.ChangedUtils
import io.swagger.v3.oas.models.responses.ApiResponses
import java.util.Optional

/** Created by adarsh.sharma on 04/01/18.  */
class ApiResponseDiff(private val openApiDiff: OpenApiDiff) {
    fun diff(left: ApiResponses?, right: ApiResponses?, context: DiffContext): Optional<ChangedApiResponse> {
        val responseMapKeyDiff = MapKeyDiff.diff(left, right)
        val sharedResponseCodes = responseMapKeyDiff.sharedKey
        val resps: MutableMap<String, ChangedResponse> = mutableMapOf()

        for (responseCode in sharedResponseCodes) {
            openApiDiff
                    .responseDiff
                    .diff(left?.get(responseCode), right?.get(responseCode), context)
                    .ifPresent { changedResponse: ChangedResponse -> resps[responseCode] = changedResponse }
        }
        val changedApiResponse = ChangedApiResponse(left, right, context)
                .setIncreased(responseMapKeyDiff.increased)
                .setMissing(responseMapKeyDiff.missing)
                .setChanged(resps)

        openApiDiff
                .extensionsDiff
                .diff(left?.extensions, right?.extensions, context)
                .ifPresent { extensions: ChangedExtensions? -> changedApiResponse.extensions = extensions }
        return ChangedUtils.isChanged(changedApiResponse)
    }
}