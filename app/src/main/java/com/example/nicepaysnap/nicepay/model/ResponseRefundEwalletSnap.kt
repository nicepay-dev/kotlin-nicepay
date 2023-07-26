package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class ResponseRefundEwalletSnap(
    @SerializedName("responseCode") val responseCode: String?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("originalPartnerReferenceNo") val partnerReferenceNo : String?,
    @SerializedName("originalReferenceNo") val originalReferenceNo : String?,
    @SerializedName("refundNo") val refundNo : String?,
    @SerializedName("partnerRefundNo") val partnerRefundNo : String?,
    @SerializedName("refundAmount") val refundAmount : totalAmount
) {

    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "partnerReferenceNo" to partnerReferenceNo,
            "originalReferenceNo" to originalReferenceNo,
            "refundNo" to refundNo,
            "partnerRefundNo" to partnerRefundNo,
            "amountValue" to refundAmount.value,
            "amountCurrency" to refundAmount.currency
        )
    }

}
