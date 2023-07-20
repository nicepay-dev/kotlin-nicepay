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

    fun checkoutLink(request: String, onResult: (String?) -> Unit){
        val retrofit = RetrofitLinkClient.buildService(requestService::class.java)

        Log.e("body request ", request.toString())
        retrofit.checkoutLink(request).enqueue(
            object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<String>, response: Response<String>) {
                    Log.e("error", response.toString())
                    Log.e("error", response.body().toString())
                    val vaNumber = response.body()
                    onResult(vaNumber)
                }
            }
        )
    }
}