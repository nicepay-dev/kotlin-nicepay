package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class ResponseQrisSnap(
    @SerializedName("responseCode") val responseCode: Int?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("referenceNo") val referenceNo : String?,
    @SerializedName("partnerReferenceNo") val partnerReferenceNo : String?,
    @SerializedName("qrContent") val qrContent : String?,
    @SerializedName("qrUrl") val qrUrl : String?,
    @SerializedName("additionalInfo") val additionalInfo : BaseQrisResponseAdditionalInfo
) {

    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "referenceNo" to referenceNo,
            "partnerReferenceNo" to partnerReferenceNo,
            "qrContent" to qrContent,
            "qrUrl" to qrUrl,
            "validityPeriod" to additionalInfo.validityPeriod
        )
    }

}
