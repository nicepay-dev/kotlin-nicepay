package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class responseVaSNAP (
    @SerializedName("responseCode") val responseCode: Int?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("virtualAccountData") val virtualAccountData: virtualAccountData
){
    fun toMap(hashMap: HashMap<Any, Any>): Map<String, Any?> {
        return mapOf(
            "responseCode" to responseCode,
            "responseMessage" to responseMessage,
            "virtualAccountNo" to virtualAccountData.virtualAccountNo,
            "virtualAccountName" to virtualAccountData.virtualAccountName,
            "trxId" to virtualAccountData.trxId,
            "value" to virtualAccountData.totalAmount.value,
            "bankCd" to virtualAccountData.addInfo.bankCd,
            "vacctValidDt" to virtualAccountData.addInfo.vacctValidDt,
            "vacctValidTm" to virtualAccountData.addInfo.vacctValidTm
        )
    }
}