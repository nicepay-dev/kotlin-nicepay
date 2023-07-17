package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class virtualAccountData (
    @SerializedName("partnerServiceId") val partnerServiceId: String?,
    @SerializedName("customerNo") val customerNo: String?,
    @SerializedName("virtualAccountNo") val virtualAccountNo: String?,
    @SerializedName("virtualAccountName") val virtualAccountName: String?,
    @SerializedName("trxId") val trxId: String?,
    @SerializedName("totalAmount") val totalAmount: totalAmount,
    @SerializedName("additionalInfo") val addInfo: additionalInfo
)