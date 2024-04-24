package com.example.nicepaysnap.nicepay.model

open class BaseSnapRequestInquiry {

    var merchantId: String? = "IONPAYTEST"
    var originalPartnerReferenceNo: String? = ""
    var originalReferenceNo: String? = ""

    constructor()
    constructor(merchantId: String?, originalPartnerReferenceNo: String?, originalReferenceNo: String?) {
        this.merchantId = merchantId
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
    }

}