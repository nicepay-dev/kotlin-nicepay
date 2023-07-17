package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class additionalInfoResponse(
    @SerializedName("bankCd") val bankCd: String?,
    @SerializedName("tXidVA") val tXidVA: String?,
    @SerializedName("goodsNm") val goodsNm: String?,
    @SerializedName("vacctValidDt") val vacctValidDt: String?,
    @SerializedName("vacctValidTm") val vacctValidTm: String?,
    @SerializedName("msId") val msId: String?,
    @SerializedName("msFee") val msFee: String?,
    @SerializedName("msFeeType") val msFeeType: String?,
    @SerializedName("mbFee") val mbFee: String?,
    @SerializedName("mbFeeType") val mbFeeType: String?
)
