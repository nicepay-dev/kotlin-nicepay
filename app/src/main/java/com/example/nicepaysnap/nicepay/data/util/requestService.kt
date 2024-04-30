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

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/qr/qr-mpm-generate")
    fun generateQr(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestQrisRegister): Call<ResponseQrisSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/qr/qr-mpm-query")
    fun checkStatusQr(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestQrisInquiry): Call<ResponseInquiryQrisSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/qr/qr-mpm-refund")
    fun refundQr(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestQrisRefund): Call<ResponseRefundQrisSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer/registration")
    fun generatePayout(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestPayoutRegistration): Call<ResponsePayoutSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer/inquiry")
    fun inquiryPayout(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestPayoutInquiry): Call<ResponseInquiryPayoutSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer/balance-inquiry")
    fun checkBalancePayout(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestPayoutCheckBalance): Call<ResponseCheckBalancePayoutSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer/approve")
    fun approvePayout(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestPayoutApproval): Call<ResponseApprovalPayoutSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer/reject")
    fun rejectPayout(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestPayoutApproval): Call<ResponseApprovalPayoutSnap>;

    @Headers("Content-Type: application/json")
    @POST("api/v1.0/transfer/cancel")
    fun cancelPayout(@HeaderMap headers: Map<String, String>, @Body requestBody : RequestPayoutCancel): Call<ResponseCancelPayoutSnap>;

}