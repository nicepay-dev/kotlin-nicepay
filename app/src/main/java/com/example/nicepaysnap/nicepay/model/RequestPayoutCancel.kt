package com.example.nicepaysnap.nicepay.model

class RequestPayoutCancel : BaseSnapRequestInquiry {

    constructor()
    constructor(originalPartnerReferenceNo: String?, originalReferenceNo: String?) : super(
        originalPartnerReferenceNo,
        originalReferenceNo
    )

}