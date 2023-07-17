package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class accessTokenResponse(
    @SerializedName("responseCode") val responseCode: Int?,
    @SerializedName("responseMessage") val responseMessage: String?,
    @SerializedName("accessToken") val accessToken: String?,
    @SerializedName("tokenType") val tokenType: String?,
    @SerializedName("expiresIn") val expiresIn: String?
)
