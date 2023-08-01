package com.example.nicepaysnap.view.ewallet

import com.example.nicepaysnap.nicepay.model.RequestEwalletDirectDebit
import com.example.nicepaysnap.nicepay.model.RequestEwalletInquiry
import com.example.nicepaysnap.nicepay.model.RequestEwalletRefund
import com.example.nicepaysnap.service.MethodService
import com.example.nicepaysnap.service.impl.EwalletServiceImpl
import com.example.nicepaysnap.view.BaseAppCompatActivity

open class BaseEwalletAppCompatActivity : BaseAppCompatActivity() {

    var ewalletService : MethodService
    <RequestEwalletDirectDebit, RequestEwalletInquiry, RequestEwalletRefund> = EwalletServiceImpl()

}