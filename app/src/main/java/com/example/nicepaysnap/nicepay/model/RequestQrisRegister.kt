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


}