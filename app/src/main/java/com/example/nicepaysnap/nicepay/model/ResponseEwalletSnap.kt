package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class ResponseEwalletSnap(
    @SerializedName("responseCode") val responseCode: Int?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("partnerReferenceNo") val partnerReferenceNo : String?,
    @SerializedName("originalReferenceNo") val originalReferenceNo : String?,
    @SerializedName("webRedirectUrl") val webRedirectUrl : String?,
    @SerializedName("redirectToken") val redirectToken : String?
) {

    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "partnerReferenceNo" to partnerReferenceNo,
            "originalReferenceNo" to originalReferenceNo,
            "webRedirectUrl" to webRedirectUrl,
            "redirectToken" to redirectToken
        )
    }

}
