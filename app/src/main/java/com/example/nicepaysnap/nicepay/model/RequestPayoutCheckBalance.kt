package com.example.nicepaysnap.nicepay.model

class RequestPayoutCheckBalance : BaseRequestMerchantSnap {

    var additionalInfo : additionalInfo? = com.example.nicepaysnap.nicepay.model.additionalInfo.Builder().build()

    constructor()
    constructor(merchantId: String?) : super(merchantId)

}