package com.example.nicepaysnap.nicepay.model

class RequestPayoutInquiry : BaseSnapRequestInquiry {

    var beneficiaryAccountNo: String? = ""

    constructor()
    constructor(
        merchantId: String?,
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        beneficiaryAccountNo: String?
    ) : super(merchantId, originalPartnerReferenceNo, originalReferenceNo) {
        this.beneficiaryAccountNo = beneficiaryAccountNo
    }


}