package com.example.nicepaysnap.nicepay.data.util

import android.util.Log
import com.example.nicepaysnap.nicepay.model.accessData
import com.example.nicepaysnap.nicepay.model.accessTokenResponse
import com.example.nicepaysnap.nicepay.model.responseVaSNAP
import com.example.nicepaysnap.nicepay.model.vaComponent
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
}