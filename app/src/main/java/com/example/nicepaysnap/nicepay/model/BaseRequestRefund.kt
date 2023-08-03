package com.example.nicepaysnap.nicepay.model

open class BaseRequestRefund {

    var merchantId: String? = "IONPAYTEST"
    var originalPartnerReferenceNo: String? = ""
    var originalReferenceNo: String? = ""
    var reason: String? = null
    var partnerRefundNo: String? = "test-refund-no"
    var externalStoreId: String? = ""
    var refundAmount: totalAmount? = null

    constructor()

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        reason: String?,
        partnerRefundNo: String?,
        refundAmount: totalAmount?,
    ) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.reason = reason
        this.partnerRefundNo = partnerRefundNo
        this.refundAmount = refundAmount
    }

    constructor(
        merchantId: String?,
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        reason: String?,
        partnerRefundNo: String?,
        externalStoreId: String?,
        refundAmount: totalAmount?,
    ) {
        this.merchantId = merchantId
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.reason = reason
        this.partnerRefundNo = partnerRefundNo
        this.externalStoreId = externalStoreId
        this.refundAmount = refundAmount
    }


}