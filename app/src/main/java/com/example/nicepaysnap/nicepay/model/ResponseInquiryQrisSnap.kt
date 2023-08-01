package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class ResponseInquiryQrisSnap(
    @SerializedName("responseCode") val responseCode: Int?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("originalPartnerReferenceNo") val partnerReferenceNo : String?,
    @SerializedName("originalReferenceNo") val originalReferenceNo : String?,
    @SerializedName("serviceCode") val serviceCode : String?,
    @SerializedName("latestTransactionStatus") val latestTransactionStatus : String?,
    @SerializedName("paidTime") val paidTime : String?,
    @SerializedName("goodsNm") val goodsNm : String?,
    @SerializedName("billingNm") val billingNm : String?,
    @SerializedName("amount") val transAmount : totalAmount,
) {
    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "partnerReferenceNo" to partnerReferenceNo,
            "originalReferenceNo" to originalReferenceNo,
            "serviceCode" to serviceCode,
            "latestTransactionStatus" to latestTransactionStatus,
            "amountValue" to transAmount.value,
            "amountCurrency" to transAmount.currency,
            "paidTime" to paidTime
        )
    }
}
