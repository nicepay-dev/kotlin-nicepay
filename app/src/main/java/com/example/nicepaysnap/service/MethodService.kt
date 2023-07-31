package com.example.nicepaysnap.service

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.nicepaysnap.nicepay.model.RequestEwalletDirectDebit
import com.example.nicepaysnap.nicepay.model.RequestEwalletInquiry
import com.example.nicepaysnap.nicepay.model.RequestEwalletRefund

interface MethodService {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun register(request : RequestEwalletDirectDebit): HashMap<String, String>

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun checkStatus(request : RequestEwalletInquiry): HashMap<String, String>

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun refund(request : RequestEwalletRefund): HashMap<String, String>

}