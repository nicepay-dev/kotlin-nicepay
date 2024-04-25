package com.example.nicepaysnap.nicepay.model

open class BaseRequestMerchantSnap {

    var merchantId: String? = "IONPAYTEST"

    constructor()
    constructor(merchantId: String?) {
        this.merchantId = merchantId
    }


}