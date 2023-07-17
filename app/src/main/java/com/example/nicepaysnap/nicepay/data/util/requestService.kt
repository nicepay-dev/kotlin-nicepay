package com.example.nicepaysnap.nicepay.data.util

import com.example.nicepaysnap.nicepay.model.accessData
import com.example.nicepaysnap.nicepay.model.accessTokenResponse
import com.example.nicepaysnap.nicepay.model.responseVaSNAP
import com.example.nicepaysnap.nicepay.model.vaComponent
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST

interface requestService {
    @Headers("Content-Type: application/json")
    @POST("v1.0/access-token/b2b")
    fun generateAccessToken(@HeaderMap headers: Map<String, String>, @Body accessData : accessData): Call<accessTokenResponse>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer-va/create-va")
    fun generateVirtualAccount(@HeaderMap headers: Map<String, String>, @Body vaComponent: vaComponent): Call<responseVaSNAP>;
}