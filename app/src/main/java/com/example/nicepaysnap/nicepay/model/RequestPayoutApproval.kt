package com.example.nicepaysnap.nicepay.model

class RequestPayoutApproval : BaseSnapRequestInquiry {

    constructor() : super()
    constructor(originalPartnerReferenceNo: String?, originalReferenceNo: String?) : super(
        originalPartnerReferenceNo,
        originalReferenceNo
    )


}