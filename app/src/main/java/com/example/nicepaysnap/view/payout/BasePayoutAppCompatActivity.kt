package com.example.nicepaysnap.view.payout

import com.example.nicepaysnap.nicepay.model.*
import com.example.nicepaysnap.service.MethodService
import com.example.nicepaysnap.service.impl.PayoutServiceImpl
import com.example.nicepaysnap.service.impl.QrisServiceImpl
import com.example.nicepaysnap.view.BaseAppCompatActivity

open class BasePayoutAppCompatActivity : BaseAppCompatActivity() {

    var payoutService : MethodService<RequestPayoutRegistration, RequestPayoutInquiry, RequestPayoutCancel>
    = PayoutServiceImpl()

}