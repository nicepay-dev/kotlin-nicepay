package com.example.nicepaysnap.nicepay.model

class RequestEwalletRefund : BaseRequestRefund {

    var subMerchantId: String? = ""
    var additionalInfo: RefundEwalletAdditionalInfo? = RefundEwalletAdditionalInfo()

    constructor()

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        reason: String?,
        partnerRefundNo: String?,
        refundAmount: totalAmount?,
        additionalInfo: RefundEwalletAdditionalInfo?
    ) : super(
        originalPartnerReferenceNo,
        originalReferenceNo,
        reason,
        partnerRefundNo,
        refundAmount
    ) {
        this.additionalInfo = additionalInfo
    }

}