package com.example.nicepaysnap.nicepay.model

class RequestEwalletInquiry : BaseRequestInquiry {

    var subMerchantId: String? = ""
    var transactionDate: String? = "2020-12-21T14:56:11+07:00"
    var amount: totalAmount? = null
    var additionalInfo: BaseEwalletAdditionalInfo? = null

    constructor(
        merchantId: String?,
        subMerchantId: String?,
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        serviceCode: String?,
        transactionDate: String?,
        externalStoreId: String?,
        amount: totalAmount?,
        additionalInfo: BaseEwalletAdditionalInfo?
    ) {
        this.merchantId = merchantId
        this.subMerchantId = subMerchantId
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.serviceCode = serviceCode
        this.transactionDate = transactionDate
        this.externalStoreId = externalStoreId
        this.amount = amount
        this.additionalInfo = additionalInfo
    }

    constructor(
        originalPartnerReferenceNo: String?,
        originalReferenceNo: String?,
        transactionDate: String?,
        amount: totalAmount?
    ) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo
        this.originalReferenceNo = originalReferenceNo
        this.transactionDate = transactionDate
        this.amount = amount
    }

    override fun toString(): String {
        return "RequestEwalletInquiry(merchantId=$merchantId, subMerchantId=$subMerchantId, originalPartnerReferenceNo=$originalPartnerReferenceNo, originalReferenceNo=$originalReferenceNo, serviceCode=$serviceCode, transactionDate=$transactionDate, externalStoreId=$externalStoreId, amount=$amount, additionalInfo=$additionalInfo)"
    }


}