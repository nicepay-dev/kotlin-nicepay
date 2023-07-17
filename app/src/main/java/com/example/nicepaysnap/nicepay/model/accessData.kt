package com.example.nicepaysnap.nicepay.model

import com.google.gson.annotations.SerializedName

data class accessData(
    @SerializedName("grantType") val grantType: String?
)
