package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

class ResponseCheckBalancePayoutSnap(
    @SerializedName("responseCode") val responseCode: String?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("availableBalance") val availableBalance : totalAmount,
) {
    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "availableBalance" to availableBalance.value
        )
    }
}