package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

class ResponseInquiryPayoutSnap(
    @SerializedName("responseCode") val responseCode: String?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("originalPartnerReferenceNo") val partnerReferenceNo : String?,
    @SerializedName("originalReferenceNo") val originalReferenceNo : String?,
    @SerializedName("beneficiaryAccountNo") val beneficiaryAccountNo : String?,
    @SerializedName("beneficiaryName") val beneficiaryName : String?,
    @SerializedName("beneficiaryBankCode") val beneficiaryBankCode : String?,
    @SerializedName("beneficiaryCustomerResidence") val beneficiaryCustomerResidence : String?,
    @SerializedName("beneficiaryCustomerType") val beneficiaryCustomerType : String?,
    @SerializedName("transactionDate") val transactionDate : String?,
    @SerializedName("latestTransactionStatus") val latestTransactionStatus : String?,
    @SerializedName("transactionStatusDesc") val transactionStatusDesc : String?,
    @SerializedName("amount") val transAmount : totalAmount,
) {
    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "partnerReferenceNo" to partnerReferenceNo,
            "originalReferenceNo" to originalReferenceNo,
            "latestTransactionStatus" to latestTransactionStatus,
            "transactionStatusDesc" to transactionStatusDesc,
            "transactionDate" to transactionDate,
            "beneficiaryAccountNo" to beneficiaryAccountNo,
            "beneficiaryName" to beneficiaryName,
            "beneficiaryBankCode" to beneficiaryBankCode,
            "beneficiaryCustomerResidence" to beneficiaryCustomerResidence,
            "beneficiaryCustomerType" to beneficiaryCustomerType,
            "amount" to transAmount.value,
        )
    }
}