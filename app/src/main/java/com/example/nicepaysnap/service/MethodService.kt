package com.example.nicepaysnap.service

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.model.RequestEwalletDirectDebit
import com.example.nicepaysnap.nicepay.model.RequestEwalletInquiry
import com.example.nicepaysnap.nicepay.model.RequestEwalletRefund

interface MethodService<T, U, V> {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun register(request : T): HashMap<String, String>

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun checkStatus(request : U): HashMap<String, String>

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun refund(request : V): HashMap<String, String>

}