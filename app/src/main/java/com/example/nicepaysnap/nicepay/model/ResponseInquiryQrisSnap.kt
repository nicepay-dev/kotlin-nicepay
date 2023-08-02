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
    @SerializedName("amount") val transAmount : totalAmount,
    @SerializedName("additionalInfo") val additionalInfo : BaseAdditionalInfo,
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
            "mitraCd" to additionalInfo.mitraCd,
            "paidTime" to paidTime,
            "goodsNm" to additionalInfo.goodsNm,
            "billingNm" to additionalInfo.billingNm
        )
    }
}
