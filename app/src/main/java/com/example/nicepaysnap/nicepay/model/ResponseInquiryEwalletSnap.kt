package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class ResponseInquiryEwalletSnap(
    @SerializedName("responseCode") val responseCode: String?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("originalPartnerReferenceNo") val partnerReferenceNo : String?,
    @SerializedName("originalReferenceNo") val originalReferenceNo : String?,
    @SerializedName("serviceCode") val serviceCode : String?,
    @SerializedName("latestTransactionStatus") val latestTransactionStatus : String?,
    @SerializedName("transactionStatusDesc") val transactionStatusDesc : String?,
    @SerializedName("transAmount") val transAmount : totalAmount,
    @SerializedName("additionalInfo") val additionalInfo : BaseEwalletAdditionalInfo
) {

    constructor(
        responseCode: String, responseMessage: String
    ) : this(responseCode, responseMessage, null, null, null,
        null, null, totalAmount.Builder().build(), BaseEwalletAdditionalInfo())

    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "partnerReferenceNo" to partnerReferenceNo,
            "originalReferenceNo" to originalReferenceNo,
            "serviceCode" to serviceCode,
            "latestTransactionStatus" to latestTransactionStatus,
            "transactionStatusDesc" to transactionStatusDesc,
            "amountValue" to transAmount.value,
            "amountCurrency" to transAmount.currency,
            "mitraCd" to additionalInfo.mitraCd,
            "billingPhone" to additionalInfo.billingPhone
        )
    }

}
