package com.example.nicepaysnap.view.payout.approval

import com.example.nicepaysnap.nicepay.model.RequestPayoutApproval
import com.example.nicepaysnap.nicepay.model.RequestPayoutCheckBalance
import com.example.nicepaysnap.service.ApprovalService
import com.example.nicepaysnap.service.impl.PayoutApprovalServiceImpl
import com.example.nicepaysnap.view.BaseAppCompatActivity

open class BasePayoutApprovalAppCompatActivity : BaseAppCompatActivity() {

    var approvalService : ApprovalService<RequestPayoutApproval, RequestPayoutCheckBalance> = PayoutApprovalServiceImpl()

    val DEFAULT_MERCHANT_ID = "IONPAYTEST"

}