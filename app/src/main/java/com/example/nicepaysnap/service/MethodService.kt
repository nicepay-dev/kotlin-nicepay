package com.example.nicepaysnap.service

import com.example.nicepaysnap.nicepay.model.RequestEwalletDirectDebit
import com.example.nicepaysnap.nicepay.model.RequestEwalletInquiry
import com.example.nicepaysnap.nicepay.model.RequestEwalletRefund

interface MethodService {

    suspend fun register(request : RequestEwalletDirectDebit): HashMap<String, String>

    suspend fun checkStatus(request : RequestEwalletInquiry): HashMap<String, String>

    suspend fun refund(request : RequestEwalletRefund): HashMap<String, String>

}