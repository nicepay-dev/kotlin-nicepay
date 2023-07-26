package com.example.nicepaysnap.nicepay.model

class RequestEwalletRefund {

    var merchantId: String? = "IONPAYTEST"
    var subMerchantId: String? = ""
    var originalPartnerReferenceNo: String? = ""
    var originalReferenceNo: String? = ""
    var reason: String? = null
    var partnerRefundNo: String? = "test-refund-no"
    var externalStoreId: String? = ""
    var refundAmount: totalAmount? = null
    var additionalInfo: RefundEwalletAdditionalInfo? = null

    constructor()
    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        reason: String?,
        partnerRefundNo: String?,
        refundAmount: totalAmount?,
        additionalInfo: RefundEwalletAdditionalInfo?
    ) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.reason = reason
        this.partnerRefundNo = partnerRefundNo
        this.refundAmount = refundAmount
        this.additionalInfo = additionalInfo
    }


}