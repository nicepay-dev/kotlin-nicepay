package com.example.nicepaysnap.nicepay.data.util

import android.util.Log
import com.example.nicepaysnap.nicepay.model.*
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class restApiService {
    fun generateAccessToken(headers: Map<String, String>,accessData: accessData, onResult: (accessTokenResponse?) -> Unit){
        val retrofit = retrofitClient.buildService(requestService::class.java)
        retrofit.generateAccessToken(headers, accessData).enqueue(
            object : Callback<accessTokenResponse> {
                override fun onFailure(call: Call<accessTokenResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<accessTokenResponse>, response: Response<accessTokenResponse>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val newAccessToken = response.body()
                    onResult(newAccessToken)
                }
            }
        )
    }

    fun generateVirtualAccount(headers: Map<String, String>,vaComponent: vaComponent, onResult: (responseVaSNAP?) -> Unit){
        val retrofit = retrofitClient.buildService(requestService::class.java)
        retrofit.generateVirtualAccount(headers, vaComponent).enqueue(
            object : Callback<responseVaSNAP> {
                override fun onFailure(call: Call<responseVaSNAP>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<responseVaSNAP>, response: Response<responseVaSNAP>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val vaNumber = response.body()
                    onResult(vaNumber)
                }
            }
        )
    }

    fun generateEwalletDirectDebit(headers: Map<String, String>, request: RequestEwalletDirectDebit, onResult: (ResponseEwalletSnap?) -> Unit){
        val retrofit = retrofitClient.buildService(requestService::class.java)

        Log.e("body request", Gson().toJson(request))
        retrofit.eWalletDirectDebit(headers, request).enqueue(
            object : Callback<ResponseEwalletSnap> {
                override fun onFailure(call: Call<ResponseEwalletSnap>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ResponseEwalletSnap>, response: Response<ResponseEwalletSnap>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val vaNumber = response.body()
                    onResult(vaNumber)
                }
            }
        )
    }

    fun checkStatusEwallet(headers: Map<String, String>, request: RequestEwalletInquiry, onResult: (ResponseInquiryEwalletSnap?) -> Unit){
        val retrofit = retrofitClient.buildService(requestService::class.java)

        Log.e("body request ", Gson().toJson(request))
        retrofit.eWalletCheckStatus(headers, request).enqueue(
            object : Callback<ResponseInquiryEwalletSnap> {
                override fun onFailure(call: Call<ResponseInquiryEwalletSnap>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ResponseInquiryEwalletSnap>, response: Response<ResponseInquiryEwalletSnap>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val vaNumber = response.body()
                    onResult(vaNumber)
                }
            }
        )
    }

    fun refundEwallet(headers: Map<String, String>, request: RequestEwalletRefund, onResult: (ResponseRefundEwalletSnap?) -> Unit){
        val retrofit = retrofitClient.buildService(requestService::class.java)

        Log.e("body request ", Gson().toJson(request))
        retrofit.eWalletRefund(headers, request).enqueue(
            object : Callback<ResponseRefundEwalletSnap> {
                override fun onFailure(call: Call<ResponseRefundEwalletSnap>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ResponseRefundEwalletSnap>, response: Response<ResponseRefundEwalletSnap>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val vaNumber = response.body()
                    onResult(vaNumber)
                }
            }
        )
    }

    fun generateQr(headers: Map<String, String>, request: RequestQrisRegister, onResult: (ResponseQrisSnap?) -> Unit){
        val retrofit = retrofitClient.buildService(requestService::class.java)

        Log.e("body request", Gson().toJson(request))
        retrofit.generateQr(headers, request).enqueue(
            object : Callback<ResponseQrisSnap> {
                override fun onFailure(call: Call<ResponseQrisSnap>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ResponseQrisSnap>, response: Response<ResponseQrisSnap>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val vaNumber = response.body()
                    onResult(vaNumber)
                }
            }
        )
    }

    fun checkStatusQr(headers: Map<String, String>, request: RequestQrisInquiry, onResult: (ResponseInquiryQrisSnap?) -> Unit){
        val retrofit = retrofitClient.buildService(requestService::class.java)

        Log.e("body request", Gson().toJson(request))
        retrofit.checkStatusQr(headers, request).enqueue(
            object : Callback<ResponseInquiryQrisSnap> {
                override fun onFailure(call: Call<ResponseInquiryQrisSnap>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<ResponseInquiryQrisSnap>, response: Response<ResponseInquiryQrisSnap>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val vaNumber = response.body()
                    onResult(vaNumber)
                }
            }
        )
    }

}