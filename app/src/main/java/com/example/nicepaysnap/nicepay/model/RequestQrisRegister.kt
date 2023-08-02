package com.example.nicepaysnap.nicepay.model

class RequestQrisRegister {

    var partnerReferenceNo : String? = "reference-test"
    var amount : totalAmount? = null
    var merchantId : String? = "IONPAYTEST"
    var storeId : String? = "NICEPAY"
    var validityPeriod : String? = null
    var additionalInfo : RequestQrisAdditionalInfo? = null

    constructor()
    constructor(
        partnerReferenceNo: String?,
        amount: totalAmount?,
        validityPeriod: String?,
        additionalInfo: RequestQrisAdditionalInfo?
    ) {
        this.partnerReferenceNo = partnerReferenceNo
        this.amount = amount
        this.validityPeriod = validityPeriod
        this.additionalInfo = additionalInfo
    }

    constructor(
        partnerReferenceNo: String?,
        amount: totalAmount?,
        merchantId: String?,
        storeId: String?,
        validityPeriod: String?,
        additionalInfo: RequestQrisAdditionalInfo?
    ) {
        this.partnerReferenceNo = partnerReferenceNo
        this.amount = amount
        this.merchantId = merchantId
        this.storeId = storeId
        this.validityPeriod = validityPeriod
        this.additionalInfo = additionalInfo
    }

    constructor(
        partnerReferenceNo: String?,
        amount: totalAmount?,
        storeId: String?,
        validityPeriod: String?,
        additionalInfo: RequestQrisAdditionalInfo?
    ) {
        this.partnerReferenceNo = partnerReferenceNo
        this.amount = amount
        this.storeId = storeId
        this.validityPeriod = validityPeriod
        this.additionalInfo = additionalInfo
    }


}