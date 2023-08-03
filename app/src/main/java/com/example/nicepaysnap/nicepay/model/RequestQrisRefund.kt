package com.example.nicepaysnap.nicepay.model

class RequestQrisRefund : BaseRequestRefund {

    var additionalInfo : RefundQrisAdditionalInfo? = RefundQrisAdditionalInfo()

    constructor() : super()
    constructor(
        merchantId: String?,
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        reason: String?,
        partnerRefundNo: String?,
        externalStoreId: String?,
        refundAmount: totalAmount?,
        additionalInfo: RefundQrisAdditionalInfo?
    ) : super(
        merchantId,
        originalPartnerReferenceNo,
        originalReferenceNo,
        reason,
        partnerRefundNo,
        externalStoreId,
        refundAmount
    ) {
        this.additionalInfo = additionalInfo
    }


}