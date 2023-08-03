package com.example.nicepaysnap.view.qris

import com.example.nicepaysnap.nicepay.model.RequestQrisInquiry
import com.example.nicepaysnap.nicepay.model.RequestQrisRefund
import com.example.nicepaysnap.nicepay.model.RequestQrisRegister
import com.example.nicepaysnap.service.MethodService
import com.example.nicepaysnap.service.impl.QrisServiceImpl
import com.example.nicepaysnap.view.BaseAppCompatActivity

open class BaseQrisAppCompatActivity : BaseAppCompatActivity() {

    var qrisService : MethodService
    <RequestQrisRegister, RequestQrisInquiry, RequestQrisRefund> = QrisServiceImpl()

    val DEFAULT_STORE_ID = "NICEPAY"
    val DEFAULT_MERCHANT_ID = "IONPAYTEST"

}