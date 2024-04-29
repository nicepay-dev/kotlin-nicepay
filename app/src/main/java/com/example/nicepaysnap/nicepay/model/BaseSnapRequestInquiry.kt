package com.example.nicepaysnap.nicepay.model

open class BaseSnapRequestInquiry : BaseRequestMerchantSnap {

    var originalPartnerReferenceNo: String? = ""
    var originalReferenceNo: String? = ""

    constructor()

    constructor(merchantId: String?, originalPartnerReferenceNo: String?, originalReferenceNo: String?) {
        this.merchantId = merchantId
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
    }

    constructor(originalPartnerReferenceNo: String?, originalReferenceNo: String?) : super() {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
    }

}