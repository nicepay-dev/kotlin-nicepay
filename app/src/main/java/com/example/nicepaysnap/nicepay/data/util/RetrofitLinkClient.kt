package com.example.nicepaysnap.nicepay.data.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitLinkClient {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://payment.linkaja.id") // change this IP for te
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

}