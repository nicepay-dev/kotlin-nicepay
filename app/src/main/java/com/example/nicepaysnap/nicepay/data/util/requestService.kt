package com.example.nicepaysnap.nicepay.data.util

import com.example.nicepaysnap.nicepay.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface requestService {
    @Headers("Content-Type: application/json")
    @POST("v1.0/access-token/b2b")
    fun generateAccessToken(@HeaderMap headers: Map<String, String>, @Body accessData : accessData): Call<accessTokenResponse>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer-va/create-va")
    fun generateVirtualAccount(@HeaderMap headers: Map<String, String>, @Body vaComponent: vaComponent): Call<responseVaSNAP>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/debit/payment-host-to-host")
    fun eWalletDirectDebit(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestEwalletDirectDebit): Call<ResponseEwalletSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/debit/status")
    fun eWalletCheckStatus(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestEwalletInquiry): Call<ResponseInquiryEwalletSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/debit/refund")
    fun eWalletRefund(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestEwalletRefund): Call<ResponseRefundEwalletSnap>;
}