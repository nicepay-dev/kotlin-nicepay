package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class ResponseEwalletSnap(
    @SerializedName("responseCode") var responseCode: String? = null,
    @SerializedName("responseMessage") var responseMessage: String? = null,
    @SerializedName("partnerReferenceNo") var partnerReferenceNo: String? = null,
    @SerializedName("originalReferenceNo") var originalReferenceNo: String? = null,
    @SerializedName("webRedirectUrl") var webRedirectUrl: String? = null
) {

    fun toMap(hashMap: HashMap<Any, String>) : Map<String, String?> {
        return mapOf(
            "responseCode" to this.responseCode,
            "responseMessage" to this.responseMessage,
            "partnerReferenceNo" to this.partnerReferenceNo,
            "originalReferenceNo" to this.originalReferenceNo,
            "webRedirectUrl" to this.webRedirectUrl
        )
    }

}
